package com.fiap.lanchonete.domain.service;

import com.fiap.lanchonete.domain.pojo.MudancaEstadoPedido;
import com.fiap.lanchonete.domain.ports.in.EstadoPedidoListener;
import com.fiap.lanchonete.domain.ports.in.PedidoService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EstadoPedidoListenerImpl implements EstadoPedidoListener {

    PedidoService pedidoService;

    @Override
    public void receber(MudancaEstadoPedido mudancaEstadoPedido) {
        pedidoService.modificarEstado(mudancaEstadoPedido.codigoPedido(), mudancaEstadoPedido.estadoPedido());
    }
}
