package com.projeto_engenharia.demo.dto;

import com.projeto_engenharia.demo.enums.CategoriaProduto;

import java.math.BigDecimal;

public record ProdutoPatchDTO(
        String nome,
        String marca,
        BigDecimal preco,
        CategoriaProduto categoria,
        Integer quantidade
) {
}
