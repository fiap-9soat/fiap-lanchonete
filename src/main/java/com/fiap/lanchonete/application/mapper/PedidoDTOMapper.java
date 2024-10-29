package com.fiap.lanchonete.application.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.fiap.lanchonete.domain.mapper.PedidoMapper;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.pojo.CreatePedidoDto;

@Mapper(componentModel = "jakarta")
public interface PedidoDTOMapper extends PedidoMapper {

    @Named("localDateTimeParaInstant")
    default Instant localDateTimeParaInstant(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.of("America/Sao_Paulo");
        return localDateTime.atZone(zone).toInstant();
    }

    @Override
    @Mapping(source = "codigoCliente", target = "codigoCliente")
    // @Mapping(source = "codigoPedido", target = "codigoPedido")
    @Mapping(target = "tsUltimoPedido", expression = "java(java.time.Instant.now())")
    Pedido toDomain(CreatePedidoDto dto);
}
