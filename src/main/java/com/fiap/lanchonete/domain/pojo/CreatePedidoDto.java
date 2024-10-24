package com.fiap.lanchonete.domain.pojo;

import java.time.LocalDateTime;

import com.fiap.lanchonete.domain.model.EstadoPedido;

public record CreatePedidoDto(
                Integer codigoPedido,
                Integer codigoCliente,
                LocalDateTime tsUltimoPedido,
                EstadoPedido estadoPedido,
                PedidoAlimentoDto pedidoAlimento) {
}
