package com.fiap.lanchonete.infrastructure.rabbitmq.adapter.out;

import com.fiap.lanchonete.domain.pojo.MudancaEstadoPedido;
import com.fiap.lanchonete.domain.ports.out.EstadoPedidoEmitter;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

@ApplicationScoped
public class EstadoPedidoRMQEmitter implements EstadoPedidoEmitter {

    @Channel("pedido.estado")
    @Broadcast
    Emitter<MudancaEstadoPedido> emitter;

    private final Logger logger = Logger.getLogger(EstadoPedidoRMQEmitter.class);

    @Override
    public void emitir(MudancaEstadoPedido mudancaEstadoPedido) {
        emitter.send(mudancaEstadoPedido);
    }
}
