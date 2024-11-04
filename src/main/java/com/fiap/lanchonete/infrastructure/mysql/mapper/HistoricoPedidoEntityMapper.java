package com.fiap.lanchonete.infrastructure.mysql.mapper;

import com.fiap.lanchonete.domain.model.HistoricoPedido;
import com.fiap.lanchonete.infrastructure.mysql.entity.HistoricoPedidoEntity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "jakarta", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface HistoricoPedidoEntityMapper {

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

    @Mapping(source = "codigoPedido", target = "codigoPedido")
    @Mapping(source = "codigoCliente", target = "codigoCliente")
    @Mapping(source = "tsUltimoPedido", target = "tsUltimoPedido", qualifiedByName = "instantParaLocalDateTime")
    @Mapping(target = "tsAlter", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "pedido", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    HistoricoPedidoEntity toEntity(HistoricoPedido historicoPedido);

    @Mapping(source = "codigoPedido", target = "codigoPedido")
    @Mapping(source = "codigoCliente", target = "codigoCliente")
    @Mapping(source = "tsUltimoPedido", target = "tsUltimoPedido", qualifiedByName = "localDateTimeParaInstant")
    @Mapping(target = "tsAlter", expression = "java(java.time.Instant.now())")
    HistoricoPedido toDomain(HistoricoPedidoEntity historicoPedidoEntity);
}
