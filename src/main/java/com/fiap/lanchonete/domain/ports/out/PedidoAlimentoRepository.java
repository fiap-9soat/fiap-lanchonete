package com.fiap.lanchonete.domain.ports.out;

import com.fiap.lanchonete.domain.model.PedidoAlimento;

public interface PedidoAlimentoRepository {
    public void inserirAlimentoPedido(Integer codigoPedido, PedidoAlimento pedidoAlimento);
}
