package com.fiap.lanchonete.infrastructure.mysql.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoricoPedidoId implements Serializable {

    private Integer codigoPedido;
    private LocalDateTime tsAlter;
}
