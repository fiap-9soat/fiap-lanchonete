package com.fiap.lanchonete.domain.ports.out;

import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.pojo.CreatePedidoDto;

import java.util.List;

public interface PedidoRepository {
    void criarPedido(Pedido pedido);

    void atualizarPedido(Pedido pedido);

    void removerPedido(Pedido pedido);

    Pedido buscarPedidoPorId(Integer id);

    List<Pedido> buscarPedidosPorCodigoCliente(Integer codigoCliente);

    Integer criarPedido(CreatePedidoDto createPedidoDto);
}
