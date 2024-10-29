package com.fiap.lanchonete.domain.ports.in;

import java.util.List;

import com.fiap.lanchonete.domain.enums.EstadoPedido;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.pojo.CreatePedidoDto;
import com.fiap.lanchonete.domain.pojo.ListaPedidosDto;

public interface PedidoService {

    public ListaPedidosDto listarPedidos();

    public Integer criarPedido(CreatePedidoDto createPedidoDto) throws Exception;

    public void editarPedido(CreatePedidoDto dto) throws Exception;

    public void removerPedido(CreatePedidoDto dto) throws Exception;

    Pedido buscarPedidoPorId(Integer id);

    List<Pedido> buscarPedidosPorCodigoCliente(Integer codigoCliente);

    public void atualizar(Pedido pedido);

    public void modificarEstado(Integer id, EstadoPedido estadoPedido);

}
