package com.fiap.lanchonete.domain.ports.in;

import com.fiap.lanchonete.domain.enums.TipoAlteracao;
import com.fiap.lanchonete.domain.model.PedidoProduto;

public interface HistoricoPedidoProdutoService {
    void registrarPedidoProduto(PedidoProduto pedidoProduto, TipoAlteracao tipoAlteracao);
}
