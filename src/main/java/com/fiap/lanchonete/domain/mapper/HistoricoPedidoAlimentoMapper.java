package com.fiap.lanchonete.domain.mapper;

import com.fiap.lanchonete.domain.model.HistoricoPedidoAlimento;
import com.fiap.lanchonete.domain.model.PedidoAlimento;

public interface HistoricoPedidoAlimentoMapper {
    HistoricoPedidoAlimento fromPedidoAlimento(PedidoAlimento pedidoAlimento);
}
