package com.fiap.lanchonete.domain.ports.out;

import java.util.List;

import com.fiap.lanchonete.domain.model.Pedido;

public interface PedidoRepository {
    Integer criarPedido(Pedido pedido);

    void atualizarPedido(Pedido pedido);

    void removerPedido(Pedido pedido);

    Pedido buscarPedidoPorId(Integer id);

    List<Pedido> buscarPedidosPorCodigoCliente(Integer codigoCliente);
}
