package com.fiap.lanchonete.domain.ports.in;

import java.util.List;

import com.fiap.lanchonete.domain.model.EstadoPedido;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.pojo.CreatePedidoDto;

public interface PedidoService {

    public Integer criarPedido(Integer codigoCliente, CreatePedidoDto createPedidoDto) throws Exception;

    public void editarPedido(Integer codigoCliente, CreatePedidoDto dto) throws Exception;

    public void removerPedido(Integer codigoCliente, CreatePedidoDto dto) throws Exception;

    Pedido buscarPedidoPorId(Integer id);

    List<Pedido> buscarPedidosPorCodigoCliente(Integer codigoCliente);

    public void atualizar(Pedido pedido);

    public void modificarEstado(Integer id, EstadoPedido estadoPedido);

}
