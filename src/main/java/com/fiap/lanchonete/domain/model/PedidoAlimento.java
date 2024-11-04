package com.fiap.lanchonete.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PedidoAlimento {
    private Integer codigoPedido;
    private Short codigoTipoAlimento;
    private Short codigoAlimento;
    private Short quantidadeAlimento;
}
