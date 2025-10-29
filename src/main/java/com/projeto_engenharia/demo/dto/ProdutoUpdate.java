package com.projeto_engenharia.demo.dto;

import java.math.BigDecimal;

public record ProdutoUpdate(
        String nome,
        String marca,
        BigDecimal preco,
        Integer quantidade
) {
}
