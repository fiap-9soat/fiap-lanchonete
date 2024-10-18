package com.fiap.lanchonete.domain.pojo;

import com.fiap.lanchonete.domain.model.EstadoPedido;
import jakarta.validation.constraints.NotEmpty;

public record MudancaEstadoPedido(@NotEmpty Integer codigoPedido, @NotEmpty EstadoPedido estadoPedido) {
}
