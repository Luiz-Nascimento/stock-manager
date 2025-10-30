package com.projeto_engenharia.demo.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProdutoRequest(
        @NotBlank(message = "Nome do produto não pode ser vazio")
        @Size(max = 130, message = "Marca não pode ter mais de 130 caracteres")
        String nome,
        @NotBlank(message = "Marca não pode ser vazia")
        @Size(max = 130, message = "Marca não pode ter mais de 130 caracteres")
        String marca,
        @NotNull(message = "Preço não pode ser nulo")
        @DecimalMin(value = "0.01", message = "O preço não pode ser menor que 0.01")
        @DecimalMax(value = "5000", message = "O preço não pode exceder 5000 reais")
        BigDecimal preco,
        @PositiveOrZero(message = "Quantidade não pode ser negativa")
        Integer quantidade,
        @NotNull(message = "Data de validade não pode ser nula.")
        LocalDate validade
) {
}
