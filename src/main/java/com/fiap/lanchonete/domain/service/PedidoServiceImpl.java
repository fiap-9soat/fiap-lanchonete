package com.fiap.lanchonete.domain.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fiap.lanchonete.domain.enums.EstadoPedido;
import com.fiap.lanchonete.domain.mapper.PedidoAlimentoMapper;
import com.fiap.lanchonete.domain.mapper.PedidoMapper;
import com.fiap.lanchonete.domain.model.HistoricoPedido;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.model.PedidoAlimento;
import com.fiap.lanchonete.domain.pojo.CreatePedidoDto;
import com.fiap.lanchonete.domain.pojo.ListaPedidosDto;
import com.fiap.lanchonete.domain.ports.in.HistoricoPedidoAlimentoService;
import com.fiap.lanchonete.domain.ports.in.HistoricoPedidoService;
import com.fiap.lanchonete.domain.ports.in.PedidoService;
import com.fiap.lanchonete.domain.ports.out.PedidoAlimentoRepository;
import com.fiap.lanchonete.domain.ports.out.PedidoRepository;

import jakarta.ws.rs.NotAllowedException;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    PedidoRepository pedidoRepository;

    PedidoAlimentoRepository pedidoAlimentoRepository;

    PedidoMapper pedidoMapper;

    PedidoAlimentoMapper pedidoAlimentoMapper;

    HistoricoPedidoService historicoPedidoService;

    HistoricoPedidoAlimentoService historicoPedidoAlimentoService;

    @Override
    public ListaPedidosDto listarPedidos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarPedidos'");
    }

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
        if (pedido == null){
            throw new NotFoundException("Pedido não foi encontrado na base de dados.");
        }
        // Adiciona o pedido atual no histórico
        historicoPedidoService.registrarPedido(id, pedido);

        pedido.setEstadoPedido(estadoPedido);
        pedidoRepository.atualizarPedido(pedido);
    }

    @Override
    public Integer criarPedido(CreatePedidoDto createPedidoDto) throws Exception {
        Integer codigoPedido = null;
        List<Pedido> checaPedidoExiste;

        Pedido pedido = pedidoMapper.toDomain(createPedidoDto);
        pedido.setEstadoPedido(EstadoPedido.INICIADO);

        checaPedidoExiste = pedidoRepository.checaSeClienteJaTemPedido(pedido);
        if (checaPedidoExiste == null || checaPedidoExiste.isEmpty()) {
            codigoPedido = pedidoRepository.criarPedido(pedido);
        } else {
            codigoPedido = checaPedidoExiste.getFirst().getCodigoPedido();
        }

        historicoPedidoService.registrarPedido(codigoPedido, pedido);

        PedidoAlimento pedidoAlimento = pedidoAlimentoMapper.toDomain(createPedidoDto);
        pedidoAlimento.setCodigoPedido(codigoPedido);
        pedidoAlimentoRepository.checarSeTipoAlimentoJáExiste(pedidoAlimento);
        pedidoAlimentoRepository.inserirAlimentoPedido(pedidoAlimento);
        historicoPedidoAlimentoService.registrarPedidoAlimento(pedidoAlimento);

        return codigoPedido;
    }

    @Override
    public void editarPedido(CreatePedidoDto createPedidoDto) throws Exception {
        Pedido pedido = pedidoMapper.toDomain(createPedidoDto);
        List<Pedido> checaPedidoExiste = pedidoRepository.checaSeClienteJaTemPedido(pedido);
        if (checaPedidoExiste.isEmpty()) {
            throw new Exception("Não existe pedido a ser editado");
        }
        Integer codigoPedido = checaPedidoExiste.getFirst().getCodigoPedido();

        PedidoAlimento pedidoAlimento = pedidoAlimentoMapper.toDomain(createPedidoDto);
        pedidoAlimento.setCodigoPedido(codigoPedido);

        pedidoAlimentoRepository.editarPedidoAlimento(pedidoAlimento);
    }

    @Override
    public void removerPedido(CreatePedidoDto createPedidoDto) throws Exception {
        Pedido pedido = pedidoMapper.toDomain(createPedidoDto);
        List<Pedido> checaPedidoExiste = pedidoRepository.checaSeClienteJaTemPedido(pedido);
        if (checaPedidoExiste.isEmpty()) {
            throw new Exception("Não existe pedido a ser editado");
        }
        Integer codigoPedido = checaPedidoExiste.getFirst().getCodigoPedido();

        PedidoAlimento pedidoAlimento = pedidoAlimentoMapper.toDomain(createPedidoDto);
        pedidoAlimento.setCodigoPedido(codigoPedido);

        pedidoAlimentoRepository.removerPedidoAlimento(pedidoAlimento);

    }

    private HistoricoPedido mapeiaHistoricoPedido(Pedido pedido) {
        HistoricoPedido historico = new HistoricoPedido();

        historico.setCodigoPedido(pedido.getCodigoPedido());
        historico.setCodigoCliente(pedido.getCodigoCliente());
        historico.setEstadoPedido(pedido.getEstadoPedido());
        historico.setTsUltimoPedido(pedido.getTsUltimoPedido());
        historico.setTsAlter(Instant.now());

        return historico;
    }
}
