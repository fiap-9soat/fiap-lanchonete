package com.fiap.lanchonete.domain.ports.in;

import java.util.List;

import com.fiap.lanchonete.domain.enums.EstadoPedido;
import com.fiap.lanchonete.domain.model.ListaPedido;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.pojo.CreatePedidoDto;

public interface PedidoService {

    List<ListaPedido> listarPedidos();

    List<ListaPedido> listarPedidosPorCodigoCliente(Integer codigoCliente);

    Integer criarPedido(CreatePedidoDto createPedidoDto) throws Exception;

    void editarPedido(Integer codigoPedido, CreatePedidoDto dto) throws Exception;

    void removerPedido(Integer codigoPedido) throws Exception;

    Pedido buscarPedidoPorId(Integer id);

    void modificarEstado(Integer id, EstadoPedido estadoPedido);

}
