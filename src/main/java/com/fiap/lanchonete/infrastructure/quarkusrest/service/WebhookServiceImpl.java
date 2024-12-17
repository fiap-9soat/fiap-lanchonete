package com.fiap.lanchonete.infrastructure.quarkusrest.service;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.fiap.lanchonete.application.rest.out.MercadoPagoConsumer;
import com.fiap.lanchonete.domain.enums.EstadoPagamento;
import com.fiap.lanchonete.domain.enums.EstadoPedido;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.ports.in.PedidoService;
import com.fiap.lanchonete.domain.ports.in.WebhookService;
import com.fiap.lanchonete.domain.ports.out.PedidoRepository;
import com.mercadopago.resources.merchantorder.MerchantOrder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class WebhookServiceImpl implements WebhookService {

    @RestClient
    MercadoPagoConsumer mercadoPagoConsumer;

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    PedidoService pedidoService;

    @Override
    public void registrarNotificacao(String topic, String id) {
        MerchantOrder resposta = mercadoPagoConsumer.consultarOrdemPagamento(id);

        if (topic.equals("merchant_order") && resposta.getStatus().equals("closed")) {
            Pedido pedido = pedidoRepository.buscarPedidoPorIdExterno(Integer.valueOf(resposta.getExternalReference()));
            pedido.setEstadoPagamento(EstadoPagamento.APROVADO);
            pedidoRepository.atualizarPedido(pedido);

            pedidoService.modificarEstado(pedido.getCodigoPedido(), EstadoPedido.EM_PREPARACAO);
        } else if (topic.equals("merchant_order") && resposta.getStatus().equals("expired")) {
            Pedido pedido = pedidoRepository.buscarPedidoPorIdExterno(Integer.valueOf(resposta.getExternalReference()));
            pedido.setEstadoPagamento(EstadoPagamento.RECUSADO);
            pedidoRepository.atualizarPedido(pedido);

            pedidoService.modificarEstado(pedido.getCodigoPedido(), EstadoPedido.CANCELADO);
        }
    }
}
