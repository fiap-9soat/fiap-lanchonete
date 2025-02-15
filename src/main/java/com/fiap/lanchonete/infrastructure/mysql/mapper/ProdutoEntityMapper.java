package com.fiap.lanchonete.infrastructure.mysql.mapper;

import com.fiap.lanchonete.domain.model.Produto;
import com.fiap.lanchonete.infrastructure.mysql.entity.ProdutoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "jakarta")
public interface ProdutoEntityMapper {

    ProdutoEntityMapper INSTANCE = Mappers.getMapper(ProdutoEntityMapper.class);

    @Named("instantParaLocalDateTime")
    default LocalDateTime instantParaLocalDateTime(Instant instant) {
        ZoneId zone = ZoneId.of("America/Sao_Paulo");
        return LocalDateTime.ofInstant(instant, zone);
    }

    @Named("localDateTimeParaInstant")
    default Instant localDateTimeParaInstant(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.of("America/Sao_Paulo");
        return localDateTime.atZone(zone).toInstant();
    }

    @Mapping(source = "codigoTipoProduto", target = "codigoTipoProduto")
    @Mapping(source = "codigoProduto", target = "codigoProduto")
    @Mapping(source = "tsAlter", target = "tsAlter", qualifiedByName = "localDateTimeParaInstant")
    Produto toDomain(ProdutoEntity domain);

    @Mapping(source = "codigoTipoProduto", target = "codigoTipoProduto")
    @Mapping(source = "codigoProduto", target = "codigoProduto")
    @Mapping(source = "tsAlter", target = "tsAlter", qualifiedByName = "instantParaLocalDateTime")
    ProdutoEntity toEntity(Produto domain);

}
