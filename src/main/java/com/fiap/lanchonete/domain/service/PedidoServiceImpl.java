package com.fiap.lanchonete.domain.service;

import java.util.List;

import com.fiap.lanchonete.domain.mapper.PedidoAlimentoMapper;
import com.fiap.lanchonete.domain.mapper.PedidoMapper;
import com.fiap.lanchonete.domain.model.EstadoPedido;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.model.PedidoAlimento;
import com.fiap.lanchonete.domain.pojo.ClienteDto;
import com.fiap.lanchonete.domain.pojo.CreatePedidoDto;
import com.fiap.lanchonete.domain.ports.in.HistoricoPedidoService;
import com.fiap.lanchonete.domain.ports.in.PedidoService;
import com.fiap.lanchonete.domain.ports.out.PedidoAlimentoRepository;
import com.fiap.lanchonete.domain.ports.out.PedidoRepository;

import jakarta.ws.rs.NotAllowedException;
import lombok.AllArgsConstructor;

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
    public Integer criarPedido(Integer codigoCliente, CreatePedidoDto createPedidoDto) throws Exception {
        Integer codigoPedido = null;

        Pedido pedido = mapeiaObjetoPedido(codigoCliente, createPedidoDto);
        pedido.setEstadoPedido(EstadoPedido.INICIADO);

        // Checa se o cliente já possui pedido e retorna o codigoPedido
        List<Pedido> checaPedidoExiste = pedidoRepository.checaSeClienteJaTemPedido(codigoCliente);
        if (checaPedidoExiste.isEmpty()) {
            codigoPedido = pedidoRepository.retornaMaiorCodigoPedido(pedido);
            pedidoRepository.criarPedido(codigoPedido, pedido);
        } else {
            codigoPedido = checaPedidoExiste.getFirst().getCodigoPedido();
        }

        PedidoAlimento pedidoAlimento = pedidoAlimentoMapper.toDomain(createPedidoDto);
        pedidoAlimento.setCodigoPedido(codigoPedido);
        pedidoAlimentoRepository.checarSeTipoAlimentoJáExiste(pedidoAlimento);
        pedidoAlimentoRepository.inserirAlimentoPedido(pedidoAlimento);

        return codigoPedido;
    }

    @Override
    public void editarPedido(Integer codigoCliente, CreatePedidoDto createPedidoDto) throws Exception {
        List<Pedido> checaPedidoExiste = pedidoRepository.checaSeClienteJaTemPedido(codigoCliente);
        if (checaPedidoExiste.isEmpty()) {
            throw new Exception("Não existe pedido a ser editado");
        }
        Integer codigoPedido = checaPedidoExiste.getFirst().getCodigoPedido();

        PedidoAlimento pedidoAlimento = pedidoAlimentoMapper.toDomain(createPedidoDto);
        pedidoAlimento.setCodigoPedido(codigoPedido);

        pedidoAlimentoRepository.editarPedidoAlimento(pedidoAlimento);
    }

    @Override
    public void removerPedido(Integer codigoCliente, CreatePedidoDto createPedidoDto) throws Exception {
        List<Pedido> checaPedidoExiste = pedidoRepository.checaSeClienteJaTemPedido(codigoCliente);
        if (checaPedidoExiste.isEmpty()) {
            throw new Exception("Não existe pedido a ser editado");
        }
        Integer codigoPedido = checaPedidoExiste.getFirst().getCodigoPedido();

        PedidoAlimento pedidoAlimento = pedidoAlimentoMapper.toDomain(createPedidoDto);
        pedidoAlimento.setCodigoPedido(codigoPedido);

        pedidoAlimentoRepository.removerPedidoAlimento(pedidoAlimento);

    }

    private Pedido mapeiaObjetoPedido(Integer codigoCliente, CreatePedidoDto createPedidoDto) {
        ClienteDto clienteDto = new ClienteDto(codigoCliente);
        return pedidoMapper.toDomain(clienteDto, createPedidoDto);
    }

}
