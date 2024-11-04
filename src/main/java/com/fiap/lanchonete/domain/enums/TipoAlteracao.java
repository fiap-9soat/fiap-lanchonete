package com.fiap.lanchonete.domain.enums;

public enum TipoAlteracao {
    I("INSERCAO"),
    D("DELECAO"),
    A("ALTERACAO");

    final String codigoAlteracao;

    TipoAlteracao(String codigoAlteracao) {
        this.codigoAlteracao = codigoAlteracao;
    }
}
