package com.fiap.lanchonete.domain.mapper;

import java.util.List;

import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.model.PedidoAlimento;
import com.fiap.lanchonete.domain.pojo.ListaPedidosDto;

public interface ListaPedidoMapper {
    ListaPedidosDto fromDomain(Pedido pedido, List<PedidoAlimento> dto);
}
