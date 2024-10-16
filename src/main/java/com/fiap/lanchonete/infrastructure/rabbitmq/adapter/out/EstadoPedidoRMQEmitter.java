package com.fiap.lanchonete.infrastructure.rabbitmq.adapter.out;

import com.fiap.lanchonete.domain.pojo.MudancaEstadoPedido;
import com.fiap.lanchonete.domain.ports.out.EstadoPedidoEmitter;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@ApplicationScoped
@AllArgsConstructor
public class EstadoPedidoRMQEmitter implements EstadoPedidoEmitter {

    @Channel("pedido")
    Emitter<MudancaEstadoPedido> emitter;

    @Override
    @Outgoing("pedido.estado")
    public void emitir(MudancaEstadoPedido mudancaEstadoPedido) {
        emitter.send(mudancaEstadoPedido);
    }
}
