package com.fiap.lanchonete.domain.pojo;

import com.fiap.lanchonete.domain.enums.EstadoPedido;

import jakarta.validation.constraints.NotEmpty;

public record MudancaEstadoPedido(Integer codigoPedido, EstadoPedido estadoPedido) {
}
