package com.fiap.lanchonete.domain.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.fiap.lanchonete.domain.ports.in.*;
import org.jboss.logging.Logger;

import com.fiap.lanchonete.domain.enums.EstadoPagamento;
import com.fiap.lanchonete.domain.enums.EstadoPedido;
import com.fiap.lanchonete.domain.enums.TipoAlteracao;
import com.fiap.lanchonete.domain.mapper.PedidoProdutoMapper;
import com.fiap.lanchonete.domain.mapper.PedidoMapper;
import com.fiap.lanchonete.domain.model.Produto;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.model.PedidoProduto;
import com.fiap.lanchonete.domain.model.PedidoQrCodeDto;
import com.fiap.lanchonete.domain.pojo.ProdutoDto;
import com.fiap.lanchonete.domain.pojo.CreatePedidoDto;
import com.fiap.lanchonete.domain.pojo.ListaPedidoProdutoDto;
import com.fiap.lanchonete.domain.pojo.ListaPedidoDto;
import com.fiap.lanchonete.domain.ports.out.PedidoProdutoRepository;
import com.fiap.lanchonete.domain.ports.out.PedidoRepository;

import io.quarkus.logging.Log;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotAcceptableException;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Logger logger = Logger.getLogger(PedidoServiceImpl.class);

    PedidoRepository pedidoRepository;

    PedidoProdutoRepository pedidoProdutoRepository;

    PedidoMapper pedidoMapper;

    ProdutoService produtoService;

    PedidoProdutoMapper pedidoProdutoMapper;

    HistoricoPedidoService historicoPedidoService;

    HistoricoPedidoProdutoService historicoPedidoProdutoService;

    MetodoPagamentoService metodoPagamentoService;

    private static final String CODIGO_EXTERNO = "FIAP";

    @Override
    public Pedido buscarPedidoPorId(Integer id) {
        return pedidoRepository.buscarPedidoPorId(id);
    }

    private ListaPedidoProdutoDto calcularValorPedidoProduto(ListaPedidoProdutoDto pedidoProduto) {
        Produto produtoRelacionado = produtoService.buscarProdutoPorId(pedidoProduto.getCodigoProduto(),
                pedidoProduto.getCodigoTipoProduto());
        BigDecimal valorProduto = Objects.requireNonNullElse(produtoRelacionado.getPrecoProduto(), BigDecimal.ZERO);
        BigDecimal valorTotal = valorProduto.multiply(BigDecimal.valueOf(pedidoProduto.getQuantidadeProduto()));
        pedidoProduto.setValorProduto(valorProduto);
        pedidoProduto.setValorTotal(valorTotal);

        return pedidoProduto;
    }

    /**
     * Retorna a soma de todos os 'valores totais' para cada conjunto de produtos
     * no pedido.
     *
     * @see ListaPedidoProdutoDto#valorTotal
     */
    private BigDecimal calcularValorTotalPedido(ListaPedidoDto pedidoDto) {
        List<BigDecimal> listaValoresTotais = pedidoDto.getListaPedidoProdutos().stream()
                .map(ListaPedidoProdutoDto::getValorTotal).toList();

        return listaValoresTotais.stream().reduce(BigDecimal.valueOf(0), BigDecimal::add);
    }

    /**
     * TODO: extrair isso para uma classe de utilidade (?)
     *
     * @param listaPedidoDtos
     * @return
     */
    private List<ListaPedidoDto> preencherListaPedidos(List<ListaPedidoDto> listaPedidoDtos) {
        List<ListaPedidoDto> listaPedidoFinal = new ArrayList<>();
        for (ListaPedidoDto listaPedidoDto : listaPedidoDtos) {
            List<ListaPedidoProdutoDto> pedidoProdutosComValor = listaPedidoDto.getListaPedidoProdutos()
                    .stream()
                    .map(this::calcularValorPedidoProduto)
                    .toList();

            listaPedidoDto.setListaPedidoProdutos(pedidoProdutosComValor);

            listaPedidoDto.setValorTotal(calcularValorTotalPedido(listaPedidoDto));

            listaPedidoFinal.add(listaPedidoDto);
        }
        listaPedidoFinal.sort(Comparator.comparing(ListaPedidoDto::getEstadoPedido, Comparator.reverseOrder())
                .thenComparing(ListaPedidoDto::getTsUltimoPedido));

        return listaPedidoFinal;
    }

    @Override
    public List<ListaPedidoDto> listarPedidos() {
        List<ListaPedidoDto> pedidos = pedidoRepository.listarPedidos();

        return preencherListaPedidos(pedidos);
    }

    @Override
    public List<ListaPedidoDto> listarPedidosPorCodigoCliente(Integer codigoCliente) {
        List<ListaPedidoDto> pedidosPorCliente = pedidoRepository.buscarPedidosPorCodigoCliente(codigoCliente);

        return preencherListaPedidos(pedidosPorCliente);
    }

    @Override
    public void modificarEstado(Integer id, EstadoPedido estadoPedido) {
        logger.infof("Solicitação de mudança de estado recebida: id: %d - estado: %s", id, estadoPedido);
        Pedido pedido = pedidoRepository.buscarPedidoPorId(id);
        if (pedido == null) {
            throw new NotFoundException("Pedido não foi encontrado na base de dados.");
        }

        validarProximoEstado(pedido.getEstadoPedido(), estadoPedido);

        if (estadoPedido.equals(EstadoPedido.EM_PREPARACAO) && (pedido.getEstadoPagamento() == null
                || !pedido.getEstadoPagamento().equals(EstadoPagamento.APROVADO))) {
            throw new NotFoundException("O Pagamento precisa ser concluído para que sua preparação se inicie");
        }

        Integer codigoPedido = pedido.getCodigoPedido();
        pedido.setEstadoPedido(estadoPedido);

        // Processa e apaga o pedido caso seja FINALIZADO ou CANCELADO
        if (estadoPedido.equals(EstadoPedido.FINALIZADO) || estadoPedido.equals(EstadoPedido.CANCELADO)) {
            List<PedidoProduto> listaPedidoProdutos = pedidoProdutoRepository.listarPorCodigoPedido(codigoPedido);
            pedidoProdutoRepository.removerPorCodigoPedido(codigoPedido);

            // Adiciona produtos no histórico
            listaPedidoProdutos.forEach(
                    produto -> historicoPedidoProdutoService.registrarPedidoProduto(produto, TipoAlteracao.D));

            pedidoRepository.removerPedido(codigoPedido);
        } else {
            pedido = pedidoRepository.atualizarPedido(pedido);
        }

        // Adiciona o pedido modificado no histórico
        historicoPedidoService.registrarPedido(codigoPedido, pedido);
    }

    @Override
    public PedidoQrCodeDto criarPedido(CreatePedidoDto createPedidoDto) throws BadRequestException {
        Integer codigoPedido = null;
        List<Pedido> checaPedidoExiste;
        String idExterno;
        String uuid = UUID.randomUUID().toString();

        Pedido pedido = pedidoMapper.toDomain(createPedidoDto);
        pedido.setEstadoPedido(EstadoPedido.INICIADO);

        checaPedidoExiste = pedidoRepository.checaSeClienteJaTemPedido(pedido);
        if (checaPedidoExiste == null || checaPedidoExiste.isEmpty()) {
            codigoPedido = pedidoRepository.criarPedido(pedido);
            idExterno = CODIGO_EXTERNO + LocalDate.now().toString() + "-" + uuid;
            pedidoRepository.registrarIdPedidoExterno(codigoPedido, idExterno);
        } else {
            codigoPedido = checaPedidoExiste.getFirst().getCodigoPedido();
            idExterno = CODIGO_EXTERNO + LocalDate.now().toString() + "-" + uuid;
            pedidoRepository.registrarIdPedidoExterno(codigoPedido, idExterno);
        }

        final Integer codigoPedidoFinal = codigoPedido;

        createPedidoDto.getListaProdutos().forEach(produto -> {
            try {
                PedidoProduto pedidoProduto = pedidoProdutoMapper.toDomain(produto);
                pedidoProduto.setCodigoPedido(codigoPedidoFinal);
                pedidoProdutoRepository.checarSeTipoProdutoJaExiste(pedidoProduto);
                pedidoProdutoRepository.inserir(pedidoProduto);
                historicoPedidoProdutoService.registrarPedidoProduto(pedidoProduto, TipoAlteracao.I);
            } catch (BadRequestException e) {
                e.printStackTrace();
                throw e;
            }
        });

        var qrCode = metodoPagamentoService.gerarQrCode(idExterno, pedido, createPedidoDto.getListaProdutos());

        historicoPedidoService.registrarPedido(codigoPedido, pedido);

        return new PedidoQrCodeDto(codigoPedidoFinal, qrCode.getQrCode());
    }

    @Override
    public void editarPedido(Integer codigoPedido, CreatePedidoDto createPedidoDto) throws Exception {
        // Lança exceção caso pedido não exista
        Pedido pedido = pedidoRepository.buscarPedidoPorId(codigoPedido);

        if (!EstadoPedido.INICIADO.equals(pedido.getEstadoPedido())) {
            throw new BadRequestException("Checkout desse pedido já realizado");
        }
        if (Objects.isNull(createPedidoDto.getListaProdutos()) || createPedidoDto.getListaProdutos().isEmpty()) {
            throw new BadRequestException("Pedido deve ter pelo menos um produto relacionado.");
        }

        for (ProdutoDto produto : createPedidoDto.getListaProdutos()) {
            PedidoProduto pedidoProduto = pedidoProdutoMapper.toDomain(produto);
            pedidoProduto.setCodigoPedido(codigoPedido);

            pedidoProdutoRepository.atualizarPedido(pedidoProduto);
            historicoPedidoProdutoService.registrarPedidoProduto(pedidoProduto, TipoAlteracao.A);
        }
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
        return Objects.equals(pedido.getEstadoPagamento(), EstadoPagamento.APROVADO);
    }

    private void validarProximoEstado(EstadoPedido estadoAtual, EstadoPedido estadoRequisitado) {
        if (estadoRequisitado.getCodigo() != Integer.sum(estadoAtual.getCodigo(), 1)) {
            throw new NotAcceptableException("Estado requisitado inválido");
        }
    }

}
