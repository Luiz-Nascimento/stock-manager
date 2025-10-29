package com.projeto_engenharia.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProdutoRequest(
        String nome,
        String marca,
        BigDecimal preco,
        Integer quantidade,
        LocalDate validade
) {
}
