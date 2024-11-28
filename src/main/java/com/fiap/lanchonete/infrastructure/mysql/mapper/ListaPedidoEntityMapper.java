package com.fiap.lanchonete.infrastructure.mysql.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.fiap.lanchonete.domain.model.ListaPedido;
import com.fiap.lanchonete.infrastructure.mysql.entity.PedidoEntity;

@Mapper(componentModel = "jakarta")
public interface ListaPedidoEntityMapper {
    ListaPedidoEntityMapper INSTANCE = Mappers.getMapper(ListaPedidoEntityMapper.class);

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
    @Mapping(source = "estadoPedido", target = "estadoPedido")
    @Mapping(source = "tsUltimoPedido", target = "tsUltimoPedido", qualifiedByName = "instantParaLocalDateTime")
    PedidoEntity toEntity(ListaPedido domain);

    @Mapping(source = "codigoPedido", target = "codigoPedido")
    @Mapping(source = "estadoPedido", target = "estadoPedido")
    @Mapping(source = "tsUltimoPedido", target = "tsUltimoPedido", qualifiedByName = "localDateTimeParaInstant")
    @Mapping(target = "listaPedidos", ignore = true)
    ListaPedido toDomain(PedidoEntity domain);
}
