package com.fiap.lanchonete.domain.pojo;

import java.util.List;

public record ListarPedidoDto(
        List<PedidoAlimentoDto> listaPedidos) {
}
