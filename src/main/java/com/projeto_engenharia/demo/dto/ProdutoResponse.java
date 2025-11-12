package com.projeto_engenharia.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto_engenharia.demo.enums.CategoriaProduto;

import java.math.BigDecimal;
import java.util.Date;

public record ProdutoResponse(
        Long id,
        String nome,
        String marca,
        BigDecimal preco,
        Integer quantidade,
        CategoriaProduto categoria,
        @JsonFormat(pattern = "yyyy/MM/dd")
        Date validade
) {
}
