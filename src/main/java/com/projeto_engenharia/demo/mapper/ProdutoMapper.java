package com.projeto_engenharia.demo.mapper;

import com.projeto_engenharia.demo.dto.ProdutoRequest;
import com.projeto_engenharia.demo.dto.ProdutoResponse;
import com.projeto_engenharia.demo.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoResponse toDTO(Produto produto);
    List<ProdutoResponse> toDTOList(List<Produto> produtos);

    @Mapping(target = "marca", source = "marca")
    Produto toEntity(ProdutoRequest request);

}
