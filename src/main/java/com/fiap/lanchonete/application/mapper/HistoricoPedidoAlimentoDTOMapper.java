package com.fiap.lanchonete.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fiap.lanchonete.domain.mapper.HistoricoPedidoAlimentoMapper;
import com.fiap.lanchonete.domain.model.HistoricoPedidoAlimento;
import com.fiap.lanchonete.domain.model.PedidoAlimento;

@Mapper(componentModel = "jakarta")
public interface HistoricoPedidoAlimentoDTOMapper extends HistoricoPedidoAlimentoMapper {
    @Override
    @Mapping(target = "tsAlter", expression = "java(java.time.Instant.now())")
    HistoricoPedidoAlimento fromPedidoAlimento(PedidoAlimento pedidoAlimento);
}
