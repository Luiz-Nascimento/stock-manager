package com.projeto_engenharia.demo.mapper;

import com.projeto_engenharia.demo.dto.ProdutoRequest;
import com.projeto_engenharia.demo.dto.ProdutoResponse;
import com.projeto_engenharia.demo.dto.ProdutoUpdate;
import com.projeto_engenharia.demo.model.Produto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoResponse toDTO(Produto produto);
    List<ProdutoResponse> toDTOList(List<Produto> produtos);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    void updateFromDTO(ProdutoUpdate dto, @MappingTarget Produto produto);

    @Mapping(target = "marca", source = "marca")
    Produto toEntity(ProdutoRequest request);

}
