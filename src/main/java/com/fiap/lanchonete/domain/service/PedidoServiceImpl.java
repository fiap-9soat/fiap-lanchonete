package com.fiap.lanchonete.domain.service;

import com.fiap.lanchonete.domain.model.EstadoPedido;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.ports.in.PedidoService;
import com.fiap.lanchonete.domain.ports.out.PedidoRepository;
import jakarta.ws.rs.NotAllowedException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    PedidoRepository pedidoRepository;

    @Override
    public void atualizar(Pedido pedido) {
        Pedido pedidoPersistido = pedidoRepository.buscarPedidoPorId(pedido.getCodigoPedido());
        if (pedido.getEstadoPedido() != pedidoPersistido.getEstadoPedido()) {
            throw new NotAllowedException("O estado do pedido só pode ser alterado por eventos.");
        }

        pedidoRepository.atualizarPedido(pedido);
    }

    @Override
    public void modificarEstado(Integer id, EstadoPedido estadoPedido) {
        Pedido pedido = pedidoRepository.buscarPedidoPorId(id);
        pedido.setEstadoPedido(estadoPedido);
        pedidoRepository.atualizarPedido(pedido);
    }
}
