package com.microservice.quarkus.domain.model;

import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Getter
@Setter
public class HistoricoPedido {
    
    private Integer codigoPedido;
    private Integer codigoCliente;
    private Instant tsPedido;
    private Instant tsAlter;
    private Short codigoTipoAlimento;
    private Short codigoAlimento;
    private EstadoPedido estadoPedido;
}
