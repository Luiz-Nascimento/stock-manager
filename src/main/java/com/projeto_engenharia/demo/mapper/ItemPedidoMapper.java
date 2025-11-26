package com.projeto_engenharia.demo.mapper;

import com.projeto_engenharia.demo.dto.ItemPedidoResponse;
import com.projeto_engenharia.demo.model.ItemPedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {

    @Mapping(source = "produto.id", target = "idProduto")
    @Mapping(source = "valorTotal", target = "precoTotal")
    ItemPedidoResponse toDto(ItemPedido itemPedido);

    List<ItemPedidoResponse> toDtoList(List<ItemPedido> itemPedidos);
}
