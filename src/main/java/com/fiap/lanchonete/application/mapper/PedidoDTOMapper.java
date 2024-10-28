package com.fiap.lanchonete.application.mapper;

import com.fiap.lanchonete.domain.mapper.PedidoMapper;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.pojo.ClienteDto;
import com.fiap.lanchonete.domain.pojo.CreatePedidoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "jakarta")
public interface PedidoDTOMapper extends PedidoMapper {

    @Named("localDateTimeParaInstant")
    default Instant localDateTimeParaInstant(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.of("America/Sao_Paulo");
        return localDateTime.atZone(zone).toInstant();
    }

    @Override
    @Mapping(source = "clienteDto.codigoCliente", target = "codigoCliente")
    @Mapping(target = "tsUltimoPedido", expression = "java(java.time.Instant.now())")
    Pedido toDomain(ClienteDto clienteDto, CreatePedidoDto dto);
}
