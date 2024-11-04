package com.fiap.lanchonete.domain.ports.out;

import com.fiap.lanchonete.domain.pojo.MudancaEstadoPedido;

public interface EstadoPedidoEmitter {

    public void emitir(MudancaEstadoPedido mudancaEstadoPedido);

}
