package com.fiap.lanchonete.infrastructure.mysql.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TipoProdutoEntityId implements Serializable {
    private Short codigoTipoProduto;
    private String nomeTipoProduto;
}
