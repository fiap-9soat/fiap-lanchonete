package com.fiap.lanchonete.domain.ports.out;

import com.fiap.lanchonete.domain.model.HistoricoPedidoAlimento;

public interface HistoricoPedidoAlimentoRepository {
    void insert(HistoricoPedidoAlimento historicoPedido);
}
