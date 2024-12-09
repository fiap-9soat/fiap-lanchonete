package com.fiap.lanchonete.domain.service;

import java.util.Comparator;
import java.util.List;

import com.fiap.lanchonete.domain.enums.EstadoPagamento;
import com.fiap.lanchonete.domain.enums.EstadoPedido;
import com.fiap.lanchonete.domain.enums.TipoAlteracao;
import com.fiap.lanchonete.domain.mapper.PedidoAlimentoMapper;
import com.fiap.lanchonete.domain.mapper.PedidoMapper;
import com.fiap.lanchonete.domain.model.ListaPedido;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.model.PedidoAlimento;
import com.fiap.lanchonete.domain.pojo.CreatePedidoDto;
import com.fiap.lanchonete.domain.ports.in.HistoricoPedidoAlimentoService;
import com.fiap.lanchonete.domain.ports.in.HistoricoPedidoService;
import com.fiap.lanchonete.domain.ports.in.PedidoService;
import com.fiap.lanchonete.domain.ports.out.PedidoAlimentoRepository;
import com.fiap.lanchonete.domain.ports.out.PedidoRepository;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotAcceptableException;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import org.jboss.logging.Logger;

@AllArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Logger logger = Logger.getLogger(PedidoServiceImpl.class);

    PedidoRepository pedidoRepository;

    PedidoAlimentoRepository pedidoAlimentoRepository;

    PedidoMapper pedidoMapper;

    PedidoAlimentoMapper pedidoAlimentoMapper;

    HistoricoPedidoService historicoPedidoService;

    HistoricoPedidoAlimentoService historicoPedidoAlimentoService;

    @Override
    public Pedido buscarPedidoPorId(Integer id) {
        return pedidoRepository.buscarPedidoPorId(id);
    }

    @Override
    public List<ListaPedido> listarPedidos() {
        List<ListaPedido> listaPedidos = pedidoRepository.listarPedidos();
        listaPedidos = listaPedidos.stream().sorted(Comparator.comparing(ListaPedido::getEstadoPedido, (s1, s2) -> {
            return s2.compareTo(s1);
        }).thenComparing(ListaPedido::getTsUltimoPedido)).toList();

        return listaPedidos;
    }

    @Override
    public List<ListaPedido> listarPedidosPorCodigoCliente(Integer codigoCliente) {
        List<ListaPedido> pedidos = pedidoRepository.buscarPedidosPorCodigoCliente(codigoCliente);
        pedidos = pedidos.stream().sorted(Comparator.comparing(ListaPedido::getEstadoPedido, (s1, s2) -> {
            return s2.compareTo(s1);
        }).thenComparing(ListaPedido::getTsUltimoPedido)).toList();

        return pedidos;
    }

    @Override
    public void modificarEstado(Integer id, EstadoPedido estadoPedido) {
        logger.infof("Solicitação de mudança de estado recebida: id: %d - estado: %s", id, estadoPedido);
        Pedido pedido = pedidoRepository.buscarPedidoPorId(id);
        if (pedido == null) {
            throw new NotFoundException("Pedido não foi encontrado na base de dados.");
        }

        validarProximoEstado(pedido.getEstadoPedido(), estadoPedido);

        if (estadoPedido.equals(EstadoPedido.EM_PREPARACAO)
                && (pedido.getEstadoPagamento() == null
                        || !pedido.getEstadoPagamento().equals(EstadoPagamento.APROVADO))) {
            throw new NotFoundException("O Pagamento precisa ser concluído para que sua preparação se inicie");
        }

        Integer codigoPedido = pedido.getCodigoPedido();
        pedido.setEstadoPedido(estadoPedido);

        // Processa e apaga o pedido caso seja FINALIZADO ou CANCELADO
        if (estadoPedido.equals(EstadoPedido.FINALIZADO) || estadoPedido.equals(EstadoPedido.CANCELADO)) {
            List<PedidoAlimento> listaPedidoAlimentos = pedidoAlimentoRepository.listarPorCodigoPedido(codigoPedido);
            pedidoAlimentoRepository.removerPorCodigoPedido(codigoPedido);

            // Adiciona alimentos no histórico
            listaPedidoAlimentos
                    .forEach(alimento -> historicoPedidoAlimentoService.registrarPedidoAlimento(alimento,
                            TipoAlteracao.D));

            pedidoRepository.removerPedido(codigoPedido);
        } else {
            pedido = pedidoRepository.atualizarPedido(pedido);
        }

        // Adiciona o pedido modificado no histórico
        historicoPedidoService.registrarPedido(codigoPedido, pedido);
    }

    @Override
    public Integer criarPedido(CreatePedidoDto createPedidoDto) throws BadRequestException {
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

        final Integer codigoPedidoFinal = codigoPedido;
        historicoPedidoService.registrarPedido(codigoPedido, pedido);

        createPedidoDto.getListaAlimentos().forEach(alimento -> {
            try {
                PedidoAlimento pedidoAlimento = pedidoAlimentoMapper.toDomain(alimento);
                pedidoAlimento.setCodigoPedido(codigoPedidoFinal);
                pedidoAlimentoRepository.checarSeTipoAlimentoJaExiste(pedidoAlimento);
                pedidoAlimentoRepository.inserir(pedidoAlimento);
                historicoPedidoAlimentoService.registrarPedidoAlimento(pedidoAlimento, TipoAlteracao.I);
            } catch (BadRequestException e) {
                e.printStackTrace();
                throw e;
            }
        });

        return codigoPedido;
    }

    @Override
    public void editarPedido(Integer codigoPedido, CreatePedidoDto createPedidoDto) throws Exception {
        // Lança exceção caso pedido não exista
        Pedido pedido = pedidoRepository.buscarPedidoPorId(codigoPedido);

        if (!EstadoPedido.INICIADO.equals(pedido.getEstadoPedido())) {
            throw new NotAcceptableException("Checkout desse pedido já realizado");
        }

        createPedidoDto.getListaAlimentos().forEach(alimento -> {
            PedidoAlimento pedidoAlimento = pedidoAlimentoMapper.toDomain(alimento);
            pedidoAlimento.setCodigoPedido(codigoPedido);

            pedidoAlimentoRepository.editar(pedidoAlimento);
            historicoPedidoAlimentoService.registrarPedidoAlimento(pedidoAlimento, TipoAlteracao.A);
        });
    }

    /**
     * Cancela e apaga o pedido.
     * 
     * @param codigoPedido
     * @throws Exception
     */
    @Override
    public void removerPedido(Integer codigoPedido) {
        modificarEstado(codigoPedido, EstadoPedido.CANCELADO);
    }

    @Override
    public Boolean consultarEstadoPagamento(Integer codigoPedido) {
        Pedido pedido = pedidoRepository.buscarPedidoPorId(codigoPedido);
        if (pedido.getEstadoPagamento().equals(EstadoPagamento.APROVADO)) {
            return true;
        }
        return false;
    }

    private void validarProximoEstado(EstadoPedido estadoAtual, EstadoPedido estadoRequisitado) {
        if (estadoRequisitado.getCodigo() != Integer.sum(estadoAtual.getCodigo(), 1)) {
            throw new NotAcceptableException("Estado requisitado inválido");
        }
    }

}
