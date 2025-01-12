package com.fiap.lanchonete.domain.ports.in;

public interface WebhookService {
    void registrarNotificacao(String topic, String id);

    void registrarNotificacaoMOCK(Integer codigoPedido);
}
