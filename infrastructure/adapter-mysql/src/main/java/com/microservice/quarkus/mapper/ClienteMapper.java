package com.microservice.quarkus.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.microservice.quarkus.dbo.ClienteEntity;
import com.microservice.quarkus.domain.model.Cliente;

@Mapper(componentModel = "cdi")
public interface ClienteMapper {

    @Mapping(source = "codigoCliente", target = "codigoCliente")
    ClienteEntity toDbo(Cliente domain);

    @Mapping(source = "codigoCliente", target = "codigoCliente")
    Cliente toDomain(ClienteEntity entity);
}
