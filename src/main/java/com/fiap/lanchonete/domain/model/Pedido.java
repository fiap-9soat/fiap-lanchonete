package com.fiap.lanchonete.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Builder
@Getter
@Setter
public class Pedido {
    private Integer codigoPedido;
    private Integer codigoCliente;
    private Instant tsUltimoPedido;
    private EstadoPedido estadoPedido;
}
