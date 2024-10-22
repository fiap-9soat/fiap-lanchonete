package com.fiap.lanchonete.domain.pojo;

import java.util.List;

import com.fiap.lanchonete.domain.model.EstadoPedido;

public record ListarPedidoDto(
        Integer codigoPedido,
        Integer codigoCliente,
        EstadoPedido estadoPedido,
        List<PedidoAgrupadoDto> listaPedidos) {
}
