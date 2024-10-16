package com.fiap.lanchonete.domain.mapper;

import com.fiap.lanchonete.domain.model.Cliente;
import com.fiap.lanchonete.domain.pojo.CreateClienteDto;

public interface ClienteMapper {

    Cliente toDomain(CreateClienteDto dto);
}
