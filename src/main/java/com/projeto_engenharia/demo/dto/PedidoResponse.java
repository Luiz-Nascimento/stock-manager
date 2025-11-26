package com.projeto_engenharia.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(
        Long id,
        List<ItemPedidoResponse> vendas,
        BigDecimal valorTotal,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime dataPedido,
        Integer quantidadeTotalItens
) {
}
