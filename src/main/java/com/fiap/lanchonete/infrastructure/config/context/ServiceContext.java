package com.fiap.lanchonete.infrastructure.config.context;

import com.fiap.lanchonete.domain.mapper.*;
import com.fiap.lanchonete.domain.ports.in.*;
import com.fiap.lanchonete.domain.ports.out.*;
import com.fiap.lanchonete.domain.service.*;
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
            PedidoAlimentoRepository pedidoAlimentoRepository,
            PedidoMapper pedidoMapper,
            PedidoAlimentoMapper pedidoAlimentoMapper, HistoricoPedidoService historicoPedidoService,
            HistoricoPedidoAlimentoService historicoPedidoAlimentoService) {
        return new PedidoServiceImpl(pedidoRepository, pedidoAlimentoRepository,
                pedidoMapper, pedidoAlimentoMapper, historicoPedidoService, historicoPedidoAlimentoService);
    }

    @Produces
    public EstadoPedidoListener EstadoPedidoListener(PedidoService pedidoService) {
        return new EstadoPedidoListenerImpl(pedidoService);
    }

    @Produces
    public ClienteService clienteService(ClienteRepository clienteRepository,
            ClienteMapper clienteMapper) {
        return new ClienteServiceImpl(clienteRepository, clienteMapper);
    }

    @Produces
    public AlimentoService alimentoService(AlimentoRepository alimentoRepository, AlimentoMapper alimentoMapper) {
        return new AlimentoServiceImpl(alimentoRepository, alimentoMapper);
    }

    @Produces
    public HistoricoPedidoService historicoPedidoService(HistoricoPedidoRepository historicoPedidoRepository,
            HistoricoPedidoMapper historicoPedidoMapper) {
        return new HistoricoPedidoServiceImpl(historicoPedidoRepository, historicoPedidoMapper);
    }

    @Produces
    public HistoricoPedidoAlimentoService historicoPedidoAlimentoService(
            HistoricoPedidoAlimentoRepository historicoPedidoAlimentoRepository,
            HistoricoPedidoAlimentoMapper historicoPedidoAlimentoMapper) {
        return new HistoricoPedidoAlimentoServiceImpl(historicoPedidoAlimentoRepository, historicoPedidoAlimentoMapper);
    }

}
