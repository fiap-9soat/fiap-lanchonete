package com.fiap.lanchonete.infrastructure.mysql.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoricoPedidoProdutoEntityId implements Serializable {
    private Integer codigoPedido;
    private Short codigoTipoProduto;
    private Short codigoProduto;
    private LocalDateTime tsalter;
}
