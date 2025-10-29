package com.projeto_engenharia.demo.dto;

import java.math.BigDecimal;

public record DashboardStatsResponse(
        Long totalProdutos,
        Long quantidadeTotalItens,
        BigDecimal valorTotalEstoque,
        Long totalMarcas,
        Long produtosEstoqueBaixo,
        Long produtosEmFalta,
        Long produtosVencidos,
        Long produtosVencendo7Dias,
        Long produtosVencendo30Dias,
        BigDecimal valorEmRisco
) {
}
