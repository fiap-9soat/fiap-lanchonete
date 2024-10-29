package com.fiap.lanchonete.domain.pojo;

import java.time.Instant;
import java.util.List;

import com.fiap.lanchonete.domain.model.PedidoAlimento;

public record ListaPedidosDto(
        Integer codigoPedido,
        Instant tsUltimoPedido,
        List<PedidoAlimento> listaPedidos) {
}
