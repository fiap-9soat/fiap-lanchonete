package com.fiap.lanchonete.domain.ports.in;


import com.fiap.lanchonete.domain.pojo.MudancaEstadoPedido;

public interface EstadoPedidoListener {
    public void receber(MudancaEstadoPedido mudancaEstadoPedido);
}
