package com.fiap.lanchonete.domain.model;

public enum EstadoPedido {
    INICIADO(0),
    RECEBIDO(1),
    EM_PREPARACAO(2),
    PRONTO(3),
    FINALIZADO(4),
    CANCELADO(-1);

    final int codigoEstado;

    EstadoPedido(int codigoEstado) {
        this.codigoEstado = codigoEstado;
    }
}
