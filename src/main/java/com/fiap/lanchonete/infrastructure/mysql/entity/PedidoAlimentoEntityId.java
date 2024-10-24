package com.fiap.lanchonete.infrastructure.mysql.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoAlimentoEntityId {
    private Integer codigoPedido;
    private Short codigoTipoAlimento;
    private Short codigoAlimento;
}
