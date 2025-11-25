package com.projeto_engenharia.demo.service;

import com.projeto_engenharia.demo.dto.*;
import jakarta.persistence.EntityNotFoundException;
import com.projeto_engenharia.demo.mapper.ProdutoMapper;
import com.projeto_engenharia.demo.model.Produto;
import org.springframework.stereotype.Service;
import com.projeto_engenharia.demo.repository.ProdutoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper produtoMapper;

    private static final int ESTOQUE_BAIXO_LIMITE = 10;

    public ProdutoService(ProdutoRepository repository, ProdutoMapper produtoMapper) {
        this.repository = repository;
        this.produtoMapper = produtoMapper;
    }

    public List<ProdutoResponse> listarTodos(String filtro) {

        List<Produto> produtos;

        if (filtro == null) {
            // 1. Nenhum filtro, retorna todos
            produtos = repository.findAll();
        } else {
            // Lógica de data (copiada do seu getDashboardStatistics)
            Date hoje = new Date();
            Calendar cal7 = Calendar.getInstance();
            cal7.setTime(hoje);
            cal7.add(Calendar.DAY_OF_MONTH, 7);
            Date daqui7Dias = cal7.getTime();

            // 2. Um 'switch' para decidir qual query chamar
            switch (filtro.toUpperCase()) {
                case "VENCIDOS":
                    produtos = repository.findByValidadeBefore(hoje);
                    break;

                case "VENCENDO_7_DIAS":
                    produtos = repository.findByValidadeBetween(hoje, daqui7Dias);
                    break;

                case "ESTOQUE_BAIXO":
                    // Busca produtos com quantidade > 0 e < 10
                    produtos = repository.findEstoqueBaixo(ESTOQUE_BAIXO_LIMITE);
                    break;

                case "EM_FALTA":
                    // Busca produtos com quantidade = 0
                    produtos = repository.findByQuantidade(0);
                    break;

                default:
                    // Se o filtro for desconhecido, retorna todos
                    produtos = repository.findAll();
                    break;
            }
        }

        // 3. Converte a lista de Entidades para DTOs
        return produtoMapper.toDTOList(produtos);
    }

    public ProdutoResponse acharPorId(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        return produtoMapper.toDTO(produto);
    }

    public ProdutoResponse criarProduto(ProdutoRequest request) {
        Produto produto = produtoMapper.toEntity(request);
        Produto produtoSalvo = repository.save(produto);

        return produtoMapper.toDTO(produtoSalvo);
    }
    @Transactional
    public ProdutoResponse atualizarProduto(Long id, ProdutoUpdate data) {
        Produto produtoBuscado = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        produtoMapper.updateFromDTO(data, produtoBuscado);
        Produto produtoAtualizado = repository.save(produtoBuscado);
        return produtoMapper.toDTO(produtoAtualizado);
    }
    @Transactional
    public ProdutoResponse editarProduto(Long id, ProdutoPatchDTO data) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        produtoMapper.patchFromDTO(data, produto);
        Produto produtoAtualizado = repository.save(produto);
        return produtoMapper.toDTO(produtoAtualizado);
    }

    public void deletarProdutoPorId(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Produto não encontrado");
        }
        repository.deleteById(id);
    }
    public DashboardStatsResponse getDashboardStatistics() {

        // Data atual
        Date hoje = new Date();

        // Data para 7 dias no futuro
        Calendar cal7 = Calendar.getInstance();
        cal7.setTime(hoje);
        cal7.add(Calendar.DAY_OF_MONTH, 7);
        Date daqui7Dias = cal7.getTime();

        // Data para 30 dias no futuro
        Calendar cal30 = Calendar.getInstance();
        cal30.setTime(hoje);
        cal30.add(Calendar.DAY_OF_MONTH, 30);
        Date daqui30Dias = cal30.getTime();

        // Métricas básicas
        return new DashboardStatsResponse(
                repository.count(),
                repository.somarQuantidadeTotal(),
                repository.calcularValorTotalEstoque(),
                repository.countDistinctMarcas(),
                repository.countByQuantidadeLessThan(10),
                repository.countByQuantidade(0),
                repository.countByValidadeBefore(hoje),
                repository.countByValidadeBetween(hoje, daqui7Dias),
                repository.countByValidadeBetween(hoje, daqui30Dias),
                repository.calcularValorEmRisco(hoje)
        );
    }

}
