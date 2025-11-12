package com.projeto_engenharia.demo.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CategoriaProduto {
    ALIMENTICIO("Alimentício"),
    BEBIDAS("Bebidas"),
    ELETRONICO("Eletrônico"),
    DECORACAO("Decoração"),
    HIGIENE_PESSOAL("Higiene Pessoal"),
    LIMPEZA("Limpeza"),
    ESCRITORIO("Escritório"),
    FERRAMENTAS("Ferramentas"),
    OUTROS("Outros");

    private final String descricao;

    CategoriaProduto(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static CategoriaProduto fromString(String value) {
        if (value == null) return null;
        String normalized = value.trim().toLowerCase();

        for (CategoriaProduto categoria : CategoriaProduto.values()) {
            if (categoria.name().equalsIgnoreCase(value) ||
                    categoria.getDescricao().equalsIgnoreCase(value)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Categoria inválida: " + value);
    }
}
