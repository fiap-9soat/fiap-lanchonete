package com.fiap.lanchonete.domain.enums;

public enum EstadoPedido {
    INICIADO(0),
    RECEBIDO(1),
    EM_PREPARACAO(2),
    PRONTO(3),
    FINALIZADO(4),
    CANCELADO(-1);

    final Integer codigoEstado;

    EstadoPedido(Integer codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public Integer getCodigo() {
        return this.codigoEstado;
    }
}
