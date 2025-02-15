package com.fiap.lanchonete.domain.ports.out;

import com.fiap.lanchonete.domain.model.HistoricoPedidoProduto;

public interface HistoricoPedidoProdutoRepository {
    void insert(HistoricoPedidoProduto historicoPedido);
}
