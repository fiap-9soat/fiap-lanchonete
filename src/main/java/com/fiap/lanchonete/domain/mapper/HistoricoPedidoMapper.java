package com.fiap.lanchonete.domain.mapper;

import com.fiap.lanchonete.domain.model.HistoricoPedido;
import com.fiap.lanchonete.domain.model.Pedido;

public interface HistoricoPedidoMapper {

    HistoricoPedido fromPedido(Pedido pedido);
}
