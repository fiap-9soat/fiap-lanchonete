package com.fiap.lanchonete.application.mapper;

import com.fiap.lanchonete.domain.mapper.HistoricoPedidoMapper;
import com.fiap.lanchonete.domain.model.HistoricoPedido;
import com.fiap.lanchonete.domain.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jakarta")
public interface HistoricoPedidoDTOMapper extends HistoricoPedidoMapper {

    @Override
    @Mapping(target = "tsAlter", expression = "java(java.time.Instant.now())")
    @Mapping(target = "tsUltimoPedido", expression = "java(java.time.Instant.now())")
    HistoricoPedido fromPedido(Pedido pedido);

}
