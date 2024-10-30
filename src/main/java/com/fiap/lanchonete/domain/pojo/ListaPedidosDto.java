package com.fiap.lanchonete.domain.pojo;

import java.time.Instant;
import java.util.List;

public record ListaPedidosDto(
        Integer codigoPedido,
        Instant tsUltimoPedido,
        List<PedidoAlimentoListaDto> listaPedidos) {
}
