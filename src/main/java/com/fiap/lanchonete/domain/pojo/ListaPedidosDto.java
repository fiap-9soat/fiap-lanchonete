package com.fiap.lanchonete.domain.pojo;

import java.time.Instant;
import java.util.List;

public record ListaPedidosDto(
        Integer codigoPedido,
        Instant tsUltimoPedido,
        Integer estadoPedido,
        List<ListaPedidoAlimentoDto> listaPedidos) {
}
