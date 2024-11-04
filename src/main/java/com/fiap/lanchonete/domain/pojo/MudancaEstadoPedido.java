package com.fiap.lanchonete.domain.pojo;

import com.fiap.lanchonete.domain.enums.EstadoPedido;

public record MudancaEstadoPedido(Integer codigoPedido, EstadoPedido estadoPedido) {
}
