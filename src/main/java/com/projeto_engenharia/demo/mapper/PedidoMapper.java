package com.projeto_engenharia.demo.mapper;

import com.projeto_engenharia.demo.dto.PedidoResponse;
import com.projeto_engenharia.demo.model.Pedido;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ItemPedidoMapper.class})
public interface PedidoMapper {


    PedidoResponse toDto(Pedido pedido);

    List<PedidoResponse> toDtoList(List<Pedido> pedidos);
}
