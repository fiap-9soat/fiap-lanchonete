package com.fiap.lanchonete.domain.mapper;

import com.fiap.lanchonete.domain.model.HistoricoPedidoProduto;
import com.fiap.lanchonete.domain.model.PedidoProduto;

public interface HistoricoPedidoProdutoMapper {
    HistoricoPedidoProduto fromPedidoProduto(PedidoProduto pedidoProduto);
}
