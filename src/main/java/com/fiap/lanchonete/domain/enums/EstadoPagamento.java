package com.fiap.lanchonete.domain.enums;

public enum EstadoPagamento {
    RECUSADO("R"),
    APROVADO("A");

    final String indicadorPagamento;

    EstadoPagamento(String indicadorPagamento) {
        this.indicadorPagamento = indicadorPagamento;
    }

    public String getIndicadorPagamento() {
        return this.indicadorPagamento;
    }

    public static EstadoPagamento fromString(String indicadorPagamento) {
        for (EstadoPagamento i : EstadoPagamento.values()) {
            if (i.getIndicadorPagamento().equals("A")) {
                return EstadoPagamento.APROVADO;
            }
        }
        return EstadoPagamento.RECUSADO;
    }

}
