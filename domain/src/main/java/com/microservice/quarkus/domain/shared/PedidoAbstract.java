package com.microservice.quarkus.domain.shared;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public abstract class PedidoAbstract {
    private Integer codigoPedido;
    private Integer codigoCliente;
    private Instant tsPedido;
    private Short codigoTipoAlimento;
    private Short codigoAlimento;
    private EstadoPedido estadoPedido;
}
