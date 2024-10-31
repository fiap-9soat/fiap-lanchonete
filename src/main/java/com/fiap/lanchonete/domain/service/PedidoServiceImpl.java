package com.fiap.lanchonete.domain.service;

import java.util.ArrayList;
import java.util.List;

import com.fiap.lanchonete.domain.enums.EstadoPedido;
import com.fiap.lanchonete.domain.mapper.PedidoAlimentoMapper;
import com.fiap.lanchonete.domain.mapper.PedidoMapper;
import com.fiap.lanchonete.domain.model.ListaPedido;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.model.PedidoAlimento;
import com.fiap.lanchonete.domain.model.PedidoAlimentoLista;
import com.fiap.lanchonete.domain.pojo.CreatePedidoDto;
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

    private void preencherListaPedido(List<ListaPedido> listaPedidosFormatada, List<Pedido> listaPedidos) {
        for (Pedido pedido : listaPedidos) {
            List<PedidoAlimentoLista> pedidoAlimentos = pedidoAlimentoRepository
                    .listarPorCodigoPedido(pedido.getCodigoPedido())
                    .stream()
                    .map(pedidoAlimentoMapper::toDomain)
                    .toList();

            ListaPedido listaPedido = ListaPedido
                    .builder()
                    .tsUltimoPedido(pedido.getTsUltimoPedido())
                    .listaPedidos(pedidoAlimentos)
                    .codigoPedido(pedido.getCodigoPedido())
                    .build();

            listaPedidosFormatada.add(listaPedido);
        }
    }

    @Override
    public Pedido buscarPedidoPorId(Integer id) {
        return pedidoRepository.buscarPedidoPorId(id);
    }

    @Override
    public List<ListaPedido> listarPedidos() {
        List<ListaPedido> listaPedidosFormatada = new ArrayList<>();
        List<Pedido> listaPedidos = pedidoRepository.listarPedidos();
        preencherListaPedido(listaPedidosFormatada, listaPedidos);

        return listaPedidosFormatada;
    }

    @Override
    public List<ListaPedido> listarPedidosPorCodigoCliente(Integer codigoCliente) {
        List<ListaPedido> listaPedidosFormatada = new ArrayList<>();
        List<Pedido> pedidos = pedidoRepository.buscarPedidosPorCodigoCliente(codigoCliente);

        preencherListaPedido(listaPedidosFormatada, pedidos);

        return listaPedidosFormatada;
    }

    @Override
    public void modificarEstado(Integer id, EstadoPedido estadoPedido) {
        Pedido pedido = pedidoRepository.buscarPedidoPorId(id);
        if (pedido == null) {
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
        pedidoAlimentoRepository.checarSeTipoAlimentoJaExiste(pedidoAlimento);
        pedidoAlimentoRepository.inserir(pedidoAlimento);
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

        pedidoAlimentoRepository.editar(pedidoAlimento);
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

        pedidoAlimentoRepository.remover(pedidoAlimento);

    }
}
