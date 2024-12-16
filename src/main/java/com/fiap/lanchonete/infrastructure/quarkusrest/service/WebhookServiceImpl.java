package com.fiap.lanchonete.infrastructure.quarkusrest.service;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.fiap.lanchonete.application.rest.out.MercadoPagoConsumer;
import com.fiap.lanchonete.domain.ports.in.WebhookService;
import com.fiap.lanchonete.domain.ports.out.PedidoRepository;
import com.mercadopago.resources.merchantorder.MerchantOrder;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WebhookServiceImpl implements WebhookService {

    @RestClient
    MercadoPagoConsumer mercadoPagoConsumer;

    PedidoRepository pedidoRepository;

    @Override
    public void registrarNotificacao(String topic, String id) {
        MerchantOrder resposta = mercadoPagoConsumer.consultarOrdemPagamento(id);

        // String externalReference = resposta.getExternalReference();
    }

}
