package com.fiap.lanchonete.application.mapper;

import com.fiap.lanchonete.domain.mapper.ClienteMapper;
import com.fiap.lanchonete.domain.model.Cliente;
import com.fiap.lanchonete.domain.pojo.CreateClienteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jakarta")
public interface ClienteDTOMapper extends ClienteMapper {

    @Mapping(target = "codigoCliente", ignore = true)
    Cliente toDomain(CreateClienteDto dto);
}
