package com.fiap.lanchonete.infrastructure.rabbitmq.adapter.in;

import com.fiap.lanchonete.domain.pojo.MudancaEstadoPedido;
import com.fiap.lanchonete.domain.ports.in.EstadoPedidoListener;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import java.util.concurrent.CompletionStage;

@AllArgsConstructor
@ApplicationScoped
public class EstadoPedidoRMQListener {

    EstadoPedidoListener listener;

    @Incoming("pedido.estado")
    public CompletionStage<Void> receber(Message<MudancaEstadoPedido> mudancaEstadoPedido) {
        listener.receber(mudancaEstadoPedido.getPayload());
        return mudancaEstadoPedido.ack();
    }
}
