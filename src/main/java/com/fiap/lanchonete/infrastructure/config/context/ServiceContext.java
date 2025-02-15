package com.fiap.lanchonete.infrastructure.config.context;

import com.fiap.lanchonete.domain.mapper.ProdutoMapper;
import com.fiap.lanchonete.domain.mapper.ClienteMapper;
import com.fiap.lanchonete.domain.mapper.HistoricoPedidoProdutoMapper;
import com.fiap.lanchonete.domain.mapper.HistoricoPedidoMapper;
import com.fiap.lanchonete.domain.mapper.PedidoProdutoMapper;
import com.fiap.lanchonete.domain.mapper.PedidoMapper;
import com.fiap.lanchonete.domain.ports.in.ProdutoService;
import com.fiap.lanchonete.domain.ports.in.ClienteService;
import com.fiap.lanchonete.domain.ports.in.HistoricoPedidoProdutoService;
import com.fiap.lanchonete.domain.ports.in.HistoricoPedidoService;
import com.fiap.lanchonete.domain.ports.in.MetodoPagamentoService;
import com.fiap.lanchonete.domain.ports.in.PedidoService;
import com.fiap.lanchonete.domain.ports.in.WebhookService;
import com.fiap.lanchonete.domain.ports.out.ProdutoRepository;
import com.fiap.lanchonete.domain.ports.out.ClienteRepository;
import com.fiap.lanchonete.domain.ports.out.HistoricoPedidoProdutoRepository;
import com.fiap.lanchonete.domain.ports.out.HistoricoPedidoRepository;
import com.fiap.lanchonete.domain.ports.out.PedidoProdutoRepository;
import com.fiap.lanchonete.domain.ports.out.PedidoRepository;
import com.fiap.lanchonete.domain.service.ProdutoServiceImpl;
import com.fiap.lanchonete.domain.service.ClienteServiceImpl;
import com.fiap.lanchonete.domain.service.HistoricoPedidoProdutoServiceImpl;
import com.fiap.lanchonete.domain.service.HistoricoPedidoServiceImpl;
import com.fiap.lanchonete.domain.service.PedidoServiceImpl;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;

/**
 * Classe responsável por "produzir" beans relacionados a services para injeção
 * de dependencia.
 */
@Dependent
public class ServiceContext {

    @Produces
    public PedidoService pedidoService(PedidoRepository pedidoRepository,
            PedidoProdutoRepository pedidoProdutoRepository,
            PedidoMapper pedidoMapper,
            ProdutoService produtoService,
            PedidoProdutoMapper pedidoProdutoMapper, HistoricoPedidoService historicoPedidoService,
            HistoricoPedidoProdutoService historicoPedidoProdutoService,
            MetodoPagamentoService metodoPagamentoService, WebhookService webhookService) {
        return new PedidoServiceImpl(pedidoRepository, pedidoProdutoRepository,
                pedidoMapper, produtoService, pedidoProdutoMapper, historicoPedidoService,
                historicoPedidoProdutoService, metodoPagamentoService);
    }

    @Produces
    public ClienteService clienteService(ClienteRepository clienteRepository,
            ClienteMapper clienteMapper) {
        return new ClienteServiceImpl(clienteRepository, clienteMapper);
    }

    @Produces
    public ProdutoService produtoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        return new ProdutoServiceImpl(produtoRepository, produtoMapper);
    }

    @Produces
    public HistoricoPedidoService historicoPedidoService(HistoricoPedidoRepository historicoPedidoRepository,
            HistoricoPedidoMapper historicoPedidoMapper) {
        return new HistoricoPedidoServiceImpl(historicoPedidoRepository, historicoPedidoMapper);
    }

    @Produces
    public HistoricoPedidoProdutoService historicoPedidoProdutoService(
            HistoricoPedidoProdutoRepository historicoPedidoProdutoRepository,
            HistoricoPedidoProdutoMapper historicoPedidoProdutoMapper) {
        return new HistoricoPedidoProdutoServiceImpl(historicoPedidoProdutoRepository, historicoPedidoProdutoMapper);
    }
}
