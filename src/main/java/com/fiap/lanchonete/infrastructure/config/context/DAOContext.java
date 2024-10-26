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
                                             PedidoEntityMapper pedidoEntityMapper) {
        return new PedidoRepositoryImpl(pedidoPanacheRepository, pedidoEntityMapper);
    }

    @Produces
    public PedidoAlimentoRepository PedidoAlimentoRepository(
        PedidoAlimentoPanacheRepository pedidoAlimentoPanacheRepository,
        PedidoAlimentoEntityMapper pedidoAlimentoEntityMapper) {
        return new PedidoAlimentoRepositoryImpl(pedidoAlimentoPanacheRepository, pedidoAlimentoEntityMapper);
    }

    @Produces
    public AlimentoRepository alimentoRepository(AlimentoPanacheRepository alimentoPanacheRepository,
                                                 AlimentoEntityMapper alimentoEntityMapper) {
        return new AlimentoRepositoryImpl(alimentoPanacheRepository, alimentoEntityMapper);
    }

    @Produces
    public HistoricoPedidoRepository historicoPedidoRepository(HistoricoPedidoPanacheRepository historicoPedidoPanacheRepository, HistoricoPedidoEntityMapper historicoPedidoEntityMapper) {
        return new HistoricoPedidoRepositoryImpl(historicoPedidoPanacheRepository, historicoPedidoEntityMapper);
    }

}
