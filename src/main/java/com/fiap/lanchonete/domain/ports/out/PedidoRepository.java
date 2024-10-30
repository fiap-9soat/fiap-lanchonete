package com.fiap.lanchonete.domain.ports.out;

import java.util.List;

import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.model.PedidoAlimento;

public interface PedidoRepository {
    Integer criarPedido(Pedido pedido);

    List<Pedido> checaSeClienteJaTemPedido(Pedido pedido);

    List<PedidoAlimento> listarPedidos();

    void atualizarPedido(Pedido pedido);

    void removerPedido(Pedido pedido);

    Pedido buscarPedidoPorId(Integer id);

    List<Pedido> buscarPedidosPorCodigoCliente(Integer codigoCliente);
}
