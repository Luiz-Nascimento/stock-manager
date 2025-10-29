package com.projeto_engenharia.demo.service;

import com.projeto_engenharia.demo.dto.ProdutoRequest;
import com.projeto_engenharia.demo.dto.ProdutoResponse;
import jakarta.persistence.EntityNotFoundException;
import com.projeto_engenharia.demo.mapper.ProdutoMapper;
import com.projeto_engenharia.demo.model.Produto;
import org.springframework.stereotype.Service;
import com.projeto_engenharia.demo.repository.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper produtoMapper;

    public ProdutoService(ProdutoRepository repository, ProdutoMapper produtoMapper) {
        this.repository = repository;
        this.produtoMapper = produtoMapper;
    }

    public List<ProdutoResponse> listarTodos() {
        return produtoMapper.toDTOList(repository.findAll());
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

    public void deletarProdutoPorId(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Produto não encontrado");
        }
        repository.deleteById(id);
    }
}
