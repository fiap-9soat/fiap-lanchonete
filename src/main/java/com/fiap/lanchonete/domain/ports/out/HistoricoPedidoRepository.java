package com.fiap.lanchonete.domain.ports.out;

import com.fiap.lanchonete.domain.model.HistoricoPedido;

import java.util.List;

public interface HistoricoPedidoRepository {

    void insert(HistoricoPedido historicoPedido);

    List<HistoricoPedido> findByCodigoPedido(Integer codigoPedido);
}
