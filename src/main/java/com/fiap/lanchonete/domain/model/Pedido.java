package com.fiap.lanchonete.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

import com.fiap.lanchonete.domain.enums.EstadoPedido;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    private Integer codigoPedido;
    private Integer codigoCliente;
    private Instant tsUltimoPedido;
    private EstadoPedido estadoPedido;
    private String estadoPagamento;
}
