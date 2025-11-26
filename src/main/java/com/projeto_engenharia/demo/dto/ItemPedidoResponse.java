package com.projeto_engenharia.demo.dto;

import java.math.BigDecimal;

public record ItemPedidoResponse(
        Long id,
        Long idProduto,
        String nomeProduto,
        Integer quantidade,
        Integer precoUnitario,
        BigDecimal precoTotal
) {
}
