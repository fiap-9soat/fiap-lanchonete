package com.fiap.lanchonete.domain.pojo;

import java.time.Instant;
import java.util.List;

import com.fiap.lanchonete.domain.model.PedidoAlimentoLista;

public record ListaPedidosDto(
        Integer codigoPedido,
        Instant tsUltimoPedido,
        List<PedidoAlimentoLista> listaPedidos) {
}
