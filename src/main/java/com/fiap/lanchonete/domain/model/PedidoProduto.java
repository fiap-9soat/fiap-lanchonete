package com.fiap.lanchonete.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class PedidoProduto {
    private Integer codigoPedido;
    private Short codigoTipoProduto;
    private Short codigoProduto;
    private Short quantidadeProduto;
}
