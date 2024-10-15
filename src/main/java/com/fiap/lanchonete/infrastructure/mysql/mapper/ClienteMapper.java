package com.fiap.lanchonete.infrastructure.mysql.mapper;

import com.fiap.lanchonete.domain.model.Cliente;
import com.fiap.lanchonete.infrastructure.mysql.entity.ClienteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface ClienteMapper {
    Cliente toDomain(ClienteEntity entity);

    ClienteEntity toEntity(Cliente domain);
}
