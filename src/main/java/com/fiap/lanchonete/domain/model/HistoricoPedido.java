package com.fiap.lanchonete.domain.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Builder
@Getter
@Setter
public class HistoricoPedido {
    private Integer codigoPedido;
    private Integer codigoCliente;
    private Instant tsPedido;
    private Instant tsAlter;
    private EstadoPedido estadoPedido;
    private Short qtdadeAlimentos;

}
