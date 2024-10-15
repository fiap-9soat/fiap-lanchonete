package com.fiap.lanchonete.application.mapper;

import com.fiap.lanchonete.application.dto.CreateClienteDto;
import com.fiap.lanchonete.domain.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jakarta")
public interface ClienteDTOMapper {

    @Mapping(target = "codigoCliente", ignore = true)
    public Cliente toDomain(CreateClienteDto dto);
}
