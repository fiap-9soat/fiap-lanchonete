package com.fiap.lanchonete.domain.ports.out;

import java.util.List;

import com.fiap.lanchonete.domain.model.Pedido;

public interface PedidoRepository {
    Integer criarPedido(Pedido pedido);

    List<Pedido> checaSeClienteJaTemPedido(Pedido pedido);

    List<Pedido> listarPedidos();

    void atualizarPedido(Pedido pedido);

    void removerPedido(Integer codigoPedido);

    Pedido buscarPedidoPorId(Integer id);

    List<Pedido> buscarPedidosPorCodigoCliente(Integer codigoCliente);
}
