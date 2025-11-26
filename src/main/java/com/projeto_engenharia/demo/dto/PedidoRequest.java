package com.projeto_engenharia.demo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record PedidoRequest(
        @NotEmpty(message = "O pedido deve ter pelo menos um item")
        @Valid
        List<ItemPedidoRequest> itens
) {
}
