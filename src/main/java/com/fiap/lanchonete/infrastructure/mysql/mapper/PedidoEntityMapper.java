package com.fiap.lanchonete.infrastructure.mysql.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "jakarta")
public interface PedidoEntityMapper {

    PedidoEntityMapper INSTANCE = Mappers.getMapper(PedidoEntityMapper.class);

}
