package com.fiap.lanchonete.domain.ports.in;

import java.util.List;

import com.fiap.lanchonete.domain.enums.EstadoPedido;
import com.fiap.lanchonete.domain.pojo.ListaPedidoDto;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.model.PedidoQrCodeDto;
import com.fiap.lanchonete.domain.pojo.CreatePedidoDto;

import jakarta.ws.rs.BadRequestException;

public interface PedidoService {

    List<ListaPedidoDto> listarPedidos();

    List<ListaPedidoDto> listarPedidosPorCodigoCliente(Integer codigoCliente);

    PedidoQrCodeDto criarPedido(CreatePedidoDto createPedidoDto) throws BadRequestException;

    void editarPedido(Integer codigoPedido, CreatePedidoDto dto) throws Exception;

    void removerPedido(Integer codigoPedido) throws Exception;

    Pedido buscarPedidoPorId(Integer id);

    void modificarEstado(Integer id, EstadoPedido estadoPedido);

    Boolean consultarEstadoPagamento(Integer codigoPedido);

}
