package com.fiap.lanchonete.infrastructure.config.context;

import com.fiap.lanchonete.domain.ports.out.*;
import com.fiap.lanchonete.infrastructure.mysql.adapter.out.*;
import com.fiap.lanchonete.infrastructure.mysql.dao.*;
import com.fiap.lanchonete.infrastructure.mysql.mapper.*;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;

/**
 * Classe responsável por "produzir" beans relacionados a DAOs (ex.:
 * repositorios) para injeção de dependencia.
 */
@Dependent
public class DAOContext {

    @Produces
    public ClienteRepository clienteRepository(ClientePanacheRepository clientePanacheRepository,
            ClienteEntityMapper clienteEntityMapper) {

        return new ClienteRepositoryImpl(clientePanacheRepository, clienteEntityMapper);
    }

    @Produces
    public PedidoRepository PedidoRepository(PedidoPanacheRepository pedidoPanacheRepository,
            PedidoEntityMapper pedidoEntityMapper, ListaPedidoEntityMapper listaPedidoEntityMapper,
            PedidoProdutoListaMapper pedidoProdutoListaMapper) {
        return new PedidoRepositoryImpl(pedidoPanacheRepository, pedidoEntityMapper, listaPedidoEntityMapper,
                pedidoProdutoListaMapper);
    }

    @Produces
    public PedidoProdutoRepository PedidoProdutoRepository(
            PedidoProdutoPanacheRepository pedidoProdutoPanacheRepository,
            PedidoProdutoEntityMapper pedidoProdutoEntityMapper) {
        return new PedidoProdutoRepositoryImpl(pedidoProdutoPanacheRepository, pedidoProdutoEntityMapper);
    }

    @Produces
    public ProdutoRepository produtoRepository(ProdutoPanacheRepository produtoPanacheRepository,
            ProdutoEntityMapper produtoEntityMapper) {
        return new ProdutoRepositoryImpl(produtoPanacheRepository, produtoEntityMapper);
    }

    @Produces
    public HistoricoPedidoRepository historicoPedidoRepository(
            HistoricoPedidoPanacheRepository historicoPedidoPanacheRepository,
            HistoricoPedidoEntityMapper historicoPedidoEntityMapper) {
        return new HistoricoPedidoRepositoryImpl(historicoPedidoPanacheRepository, historicoPedidoEntityMapper);
    }

    @Produces
    public HistoricoPedidoProdutoRepository historicoPedidoProdutoRepository(
            HistoricoPedidoProdutoPanacheRepository historicoPedidoProdutoPanacheRepository,
            HistoricoPedidoProdutoEntityMapper historicoPedidoProdutoEntityMapper) {
        return new HistoricoPedidoProdutoRepositoryImpl(historicoPedidoProdutoPanacheRepository,
                historicoPedidoProdutoEntityMapper);
    }

}
