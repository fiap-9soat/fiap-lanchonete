package com.fiap.lanchonete.infrastructure.mysql.mapper;

import com.fiap.lanchonete.domain.model.HistoricoPedido;
import com.fiap.lanchonete.infrastructure.mysql.entity.HistoricoPedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "jakarta", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface HistoricoPedidoEntityMapper {

    HistoricoPedidoEntity toEntity(HistoricoPedido historicoPedido);

    HistoricoPedido toDomain(HistoricoPedidoEntity historicoPedidoEntity);
}
