package com.fiap.lanchonete.infrastructure.mysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class PedidoEntityId implements Serializable {
    private Integer codigoPedido;
    private Integer codigoCliente;
}
