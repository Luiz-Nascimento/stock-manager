package com.projeto_engenharia.demo.repository;

import com.projeto_engenharia.demo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Conta produtos com quantidade menor que um valor
    Long countByQuantidadeLessThan(Integer quantidade);

    // Conta produtos sem estoque
    Long countByQuantidade(Integer quantidade);

    // Conta total de marcas distintas
    @Query("SELECT COUNT(DISTINCT p.marca) FROM Produto p")
    Long countDistinctMarcas();

    // Soma total de quantidades
    @Query("SELECT COALESCE(SUM(p.quantidade), 0) FROM Produto p")
    Long somarQuantidadeTotal();

    // Calcula valor total em estoque
    @Query("SELECT COALESCE(SUM(p.preco * p.quantidade), 0) FROM Produto p")
    BigDecimal calcularValorTotalEstoque();

    // Conta produtos vencidos
    Long countByValidadeBefore(Date data);

    // Conta produtos entre datas (para vencendo em X dias)
    Long countByValidadeBetween(Date inicio, Date fim);

    // Calcula valor em risco (produtos vencidos)
    @Query("SELECT COALESCE(SUM(p.preco * p.quantidade), 0) FROM Produto p WHERE p.validade < :data")
    BigDecimal calcularValorEmRisco(@Param("data") Date data);

    List<Produto> findByValidadeBefore(Date data);

    // 2. Filtro VENCENDO (equivalente ao countByValidadeBetween)
    List<Produto> findByValidadeBetween(Date inicio, Date fim);

    // 3. Filtro EM FALTA (equivalente ao countByQuantidade)
    List<Produto> findByQuantidade(Integer quantidade);

    // 4. Filtro ESTOQUE BAIXO (equivalente ao countByQuantidadeLessThan, mas > 0)
    // (É melhor usar @Query para garantir que não inclua os "EM FALTA")
    @Query("SELECT p FROM Produto p WHERE p.quantidade > 0 AND p.quantidade < :limite")
    List<Produto> findEstoqueBaixo(@Param("limite") Integer limite);
}
