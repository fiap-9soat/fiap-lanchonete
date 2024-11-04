package com.fiap.lanchonete.infrastructure.mysql.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;

import com.fiap.lanchonete.domain.model.HistoricoPedidoAlimento;
import com.fiap.lanchonete.infrastructure.mysql.entity.HistoricoPedidoAlimentoEntity;

@Mapper(componentModel = "jakarta", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface HistoricoPedidoAlimentoEntityMapper {
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

    @Mapping(source = "tipoAlter", target = "tipoAlter")
    @Mapping(source = "quantidadeAlimento", target = "qtdadeAlimento")
    @Mapping(target = "tsalter", expression = "java(java.time.LocalDateTime.now())")
    HistoricoPedidoAlimentoEntity toEntity(HistoricoPedidoAlimento historicoPedidoAlimento);
}
