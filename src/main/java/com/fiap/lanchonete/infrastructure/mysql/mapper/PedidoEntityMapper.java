package com.fiap.lanchonete.infrastructure.mysql.mapper;

import com.fiap.lanchonete.domain.enums.EstadoPagamento;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.infrastructure.mysql.entity.PedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "jakarta")
public interface PedidoEntityMapper {

    PedidoEntityMapper INSTANCE = Mappers.getMapper(PedidoEntityMapper.class);

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

    @Named("estadoPagamentoParaString")
    default String estadoPagamentoParaString(EstadoPagamento estadoPagamento) {
        return estadoPagamento.getIndicadorPagamento();
    }

    @Named("stringParaEstadoPagamento")
    default EstadoPagamento estadoPagamentoParaString(String estadoPagamento) {
        return EstadoPagamento.fromString(estadoPagamento);
    }

    @Mapping(source = "tsUltimoPedido", target = "tsUltimoPedido", qualifiedByName = "instantParaLocalDateTime")
    @Mapping(target = "pedidoAlimento", ignore = true)
    @Mapping(source = "estadoPagamento", target = "estadoPagamento", qualifiedByName = "stringParaEstadoPagamento")
    @Mapping(target = "cliente", ignore = true)
    PedidoEntity toEntity(Pedido domain);

    @Mapping(source = "codigoCliente", target = "codigoCliente")
    @Mapping(source = "codigoPedido", target = "codigoPedido")
    @Mapping(source = "estadoPedido", target = "estadoPedido")
    @Mapping(source = "estadoPagamento", target = "estadoPagamento", qualifiedByName = "estadoPagamentoParaString")
    @Mapping(source = "tsUltimoPedido", target = "tsUltimoPedido", qualifiedByName = "localDateTimeParaInstant")
    Pedido toDomain(PedidoEntity domain);
}
