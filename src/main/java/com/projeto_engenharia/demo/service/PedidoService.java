package com.projeto_engenharia.demo.service;

import com.projeto_engenharia.demo.dto.ItemPedidoRequest;
import com.projeto_engenharia.demo.dto.ItemPedidoResponse;
import com.projeto_engenharia.demo.dto.PedidoRequest;
import com.projeto_engenharia.demo.dto.PedidoResponse;
import com.projeto_engenharia.demo.mapper.ItemPedidoMapper;
import com.projeto_engenharia.demo.mapper.PedidoMapper;
import com.projeto_engenharia.demo.model.ItemPedido;
import com.projeto_engenharia.demo.model.Pedido;
import com.projeto_engenharia.demo.model.Produto;
import com.projeto_engenharia.demo.repository.ItemPedidoRepository;
import com.projeto_engenharia.demo.repository.PedidoRepository;
import com.projeto_engenharia.demo.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.ValidationAnnotationUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {


    private final ItemPedidoRepository itemPedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoMapper itemPedidoMapper;
    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;

    public PedidoService(ItemPedidoRepository itemPedidoRepository, ProdutoRepository produtoRepository, ItemPedidoMapper itemPedidoMapper, PedidoRepository pedidoRepository, PedidoMapper pedidoMapper) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.produtoRepository = produtoRepository;
        this.itemPedidoMapper = itemPedidoMapper;
        this.pedidoRepository = pedidoRepository;
        this.pedidoMapper = pedidoMapper;
    }

    @Transactional
    public PedidoResponse realizarVenda(PedidoRequest request) {
        Pedido pedido = new Pedido();

        List<ItemPedido> itensParaSalvar = new ArrayList<>();
        BigDecimal valorTotalPedido = BigDecimal.ZERO;
        int quantidadeTotalItens = 0;

        for (ItemPedidoRequest itemRequest: request.itens()) {
            Produto produto = produtoRepository.findById(itemRequest.produtoId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com id: "
                            + itemRequest.produtoId()));
            if (produto.getQuantidade() < itemRequest.quantidade()) {
                throw new IllegalArgumentException("Estoque insuficiente para: " + produto.getNome() +
                        ". Disponível: " + produto.getQuantidade());
            }
            produto.setQuantidade(produto.getQuantidade() - itemRequest.quantidade());
            produtoRepository.save(produto);

            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemRequest.quantidade());
            item.setPrecoUnitario(produto.getPreco());

            itensParaSalvar.add(item);
            valorTotalPedido = valorTotalPedido.add(item.getValorTotal());
            quantidadeTotalItens += item.getQuantidade();
        }
        pedido.setVendas(itensParaSalvar);
        pedido.setValorTotal(valorTotalPedido);
        pedido.setQuantidadeTotalItens(quantidadeTotalItens);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return pedidoMapper.toDto(pedidoSalvo);
    }

    public List<PedidoResponse> listAll() {
        return pedidoMapper.toDtoList(pedidoRepository.findAll());
    }


}
