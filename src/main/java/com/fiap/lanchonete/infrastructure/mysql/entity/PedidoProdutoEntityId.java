package com.fiap.lanchonete.infrastructure.mysql.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoProdutoEntityId implements Serializable {
    private Integer codigoPedido;
    private Short codigoTipoProduto;
    private Short codigoProduto;
}
