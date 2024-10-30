package com.fiap.lanchonete.domain.service;

import com.fiap.lanchonete.domain.pojo.MudancaEstadoPedido;
import com.fiap.lanchonete.domain.ports.in.EstadoPedidoListener;
import com.fiap.lanchonete.domain.ports.in.PedidoService;
import lombok.AllArgsConstructor;
import org.jboss.logging.Logger;

@AllArgsConstructor
public class EstadoPedidoListenerImpl implements EstadoPedidoListener {

    PedidoService pedidoService;

    private final Logger logger = Logger.getLogger(EstadoPedidoListenerImpl.class);

    @Override
    public void receber(MudancaEstadoPedido mudancaEstadoPedido) {
        logger.infof("Solicitação de mudança de estado recebida: %s", mudancaEstadoPedido);
        pedidoService.modificarEstado(mudancaEstadoPedido.codigoPedido(), mudancaEstadoPedido.estadoPedido());
    }
}
