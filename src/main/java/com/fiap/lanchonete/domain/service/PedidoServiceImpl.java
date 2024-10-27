package com.fiap.lanchonete.domain.service;

import com.fiap.lanchonete.domain.mapper.PedidoAlimentoMapper;
import com.fiap.lanchonete.domain.mapper.PedidoMapper;
import com.fiap.lanchonete.domain.model.EstadoPedido;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.model.PedidoAlimento;
import com.fiap.lanchonete.domain.pojo.CreatePedidoDto;
import com.fiap.lanchonete.domain.ports.in.HistoricoPedidoService;
import com.fiap.lanchonete.domain.ports.in.PedidoService;
import com.fiap.lanchonete.domain.ports.out.PedidoAlimentoRepository;
import com.fiap.lanchonete.domain.ports.out.PedidoRepository;
import jakarta.ws.rs.NotAllowedException;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    PedidoRepository pedidoRepository;

    PedidoAlimentoRepository pedidoAlimentoRepository;

    PedidoMapper pedidoMapper;

    PedidoAlimentoMapper pedidoAlimentoMapper;

    HistoricoPedidoService historicoPedidoService;

    @Override
    public Pedido buscarPedidoPorId(Integer id) {
        return pedidoRepository.buscarPedidoPorId(id);
    }

    @Override
    public List<Pedido> buscarPedidosPorCodigoCliente(Integer codigoCliente) {
        return pedidoRepository.buscarPedidosPorCodigoCliente(codigoCliente);
    }

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
        // Adiciona o pedido atual no histórico
        historicoPedidoService.registrarPedido(pedido);

        pedido.setEstadoPedido(estadoPedido);
        pedidoRepository.atualizarPedido(pedido);
    }

    @Override
    public Integer criarPedido(CreatePedidoDto createPedidoDto) {
        Pedido pedido = pedidoMapper.toDomain(createPedidoDto);
        pedido.setEstadoPedido(EstadoPedido.INICIADO);
        PedidoAlimento pedidoAlimento = pedidoAlimentoMapper.toDomain(createPedidoDto);


        Integer codigoPedido = pedidoRepository.criarPedido(pedido);
        pedidoAlimentoRepository.inserirAlimentoPedido(codigoPedido, pedidoAlimento);

        return codigoPedido;
    }
}
