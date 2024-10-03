package com.microservice.quarkus.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.microservice.quarkus.dbo.PedidoEntity;
import com.microservice.quarkus.domain.model.Pedido;


@Mapper(componentModel = "cdi", uses = AlimentoMapper.class)
public interface PedidoMapper {

    PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);

    @Mapping(source = "codigoPedido", target = "codigoPedido")
    @Mapping(source = "codigoCliente", target = "codigoCliente")
    @Mapping(source = "tsPedido", target = "tsPedido", qualifiedByName = "instantParaLocalDateTime")
    PedidoEntity toDbo(Pedido domain);
} 
