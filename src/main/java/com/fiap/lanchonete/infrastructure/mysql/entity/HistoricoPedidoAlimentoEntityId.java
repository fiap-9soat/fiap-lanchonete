package com.fiap.lanchonete.infrastructure.mysql.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoricoPedidoAlimentoEntityId implements Serializable {
    private Integer codigoPedido;
    private Short codigoTipoAlimento;
    private Short codigoAlimento;
    private LocalDateTime tsalter;
}
