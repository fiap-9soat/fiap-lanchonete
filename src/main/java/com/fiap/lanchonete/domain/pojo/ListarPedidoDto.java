package com.fiap.lanchonete.domain.pojo;

import java.util.List;

import com.fiap.lanchonete.domain.model.Pedido;

public record ListarPedidoDto(
        List<Pedido> listaPedidos) {
}
