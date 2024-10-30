package com.fiap.lanchonete.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.fiap.lanchonete.domain.mapper.ListaPedidoMapper;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.model.PedidoAlimento;
import com.fiap.lanchonete.domain.pojo.ListaPedidosDto;

@Mapper(componentModel = "jakarta")
public interface ListaPedidoDTOMapper extends ListaPedidoMapper {

    @Override

    ListaPedidosDto fromDomain(Pedido pedido, List<PedidoAlimento> dto);
}
