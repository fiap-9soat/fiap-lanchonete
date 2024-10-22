package com.fiap.lanchonete.domain.ports.in;

import com.fiap.lanchonete.domain.model.EstadoPedido;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.pojo.ListarPedidoDto;

import java.util.List;

public interface PedidoService {

    Pedido buscarPedidoPorId(Integer id);

    List<Pedido> buscarPedidosPorCodigoCliente(Integer codigoCliente);

    public void atualizar(Pedido pedido);

    public void modificarEstado(Integer id, EstadoPedido estadoPedido);

    public ListarPedidoDto listarPedidos();
}
