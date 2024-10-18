package com.fiap.lanchonete.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PedidoAgrupado {
    private Integer codigoPedido;
    private Integer codigoCliente;
}
