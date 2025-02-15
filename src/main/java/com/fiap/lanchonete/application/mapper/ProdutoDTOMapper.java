package com.fiap.lanchonete.application.mapper;

import com.fiap.lanchonete.domain.mapper.ProdutoMapper;
import com.fiap.lanchonete.domain.model.Produto;
import com.fiap.lanchonete.domain.pojo.CreateProdutoDto;
import com.fiap.lanchonete.domain.pojo.EditProdutoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "jakarta", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface ProdutoDTOMapper extends ProdutoMapper {

    @Named("localDateTimeParaInstant")
    default Instant localDateTimeParaInstant(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.of("America/Sao_Paulo");
        return localDateTime.atZone(zone).toInstant();
    }

    @Override
    @Mapping(source = "codigoTipoProduto", target = "codigoTipoProduto")
    @Mapping(target = "codigoProduto", ignore = true)
    @Mapping(source = "precoProduto", target = "precoProduto")
    @Mapping(target = "tsAlter", expression = "java(java.time.Instant.now())")
    Produto toDomain(CreateProdutoDto dto);

    @Override
    @Mapping(source = "codigoTipoProduto", target = "codigoTipoProduto")
    @Mapping(source = "codigoProduto", target = "codigoProduto")
    @Mapping(target = "tsAlter", expression = "java(java.time.Instant.now())")
    Produto toDomain(EditProdutoDto dto);

}
