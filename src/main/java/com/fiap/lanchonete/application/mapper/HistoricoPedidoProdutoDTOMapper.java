package com.fiap.lanchonete.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fiap.lanchonete.domain.mapper.HistoricoPedidoProdutoMapper;
import com.fiap.lanchonete.domain.model.HistoricoPedidoProduto;
import com.fiap.lanchonete.domain.model.PedidoProduto;

@Mapper(componentModel = "jakarta")
public interface HistoricoPedidoProdutoDTOMapper extends HistoricoPedidoProdutoMapper {
    @Override
    @Mapping(target = "tsAlter", expression = "java(java.time.Instant.now())")
    @Mapping(target = "tipoAlter", ignore = true)
    HistoricoPedidoProduto fromPedidoProduto(PedidoProduto pedidoProduto);
}
