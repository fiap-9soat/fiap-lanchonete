package com.fiap.lanchonete.infrastructure.config.context;

import com.fiap.lanchonete.domain.mapper.AlimentoMapper;
import com.fiap.lanchonete.domain.mapper.ClienteMapper;
import com.fiap.lanchonete.domain.mapper.HistoricoPedidoAlimentoMapper;
import com.fiap.lanchonete.domain.mapper.HistoricoPedidoMapper;
import com.fiap.lanchonete.domain.mapper.PedidoAlimentoMapper;
import com.fiap.lanchonete.domain.mapper.PedidoMapper;
import com.fiap.lanchonete.domain.ports.in.AlimentoService;
import com.fiap.lanchonete.domain.ports.in.ClienteService;
import com.fiap.lanchonete.domain.ports.in.HistoricoPedidoAlimentoService;
import com.fiap.lanchonete.domain.ports.in.HistoricoPedidoService;
import com.fiap.lanchonete.domain.ports.in.MetodoPagamentoService;
import com.fiap.lanchonete.domain.ports.in.PedidoService;
import com.fiap.lanchonete.domain.ports.in.WebhookService;
import com.fiap.lanchonete.domain.ports.out.AlimentoRepository;
import com.fiap.lanchonete.domain.ports.out.ClienteRepository;
import com.fiap.lanchonete.domain.ports.out.HistoricoPedidoAlimentoRepository;
import com.fiap.lanchonete.domain.ports.out.HistoricoPedidoRepository;
import com.fiap.lanchonete.domain.ports.out.PedidoAlimentoRepository;
import com.fiap.lanchonete.domain.ports.out.PedidoRepository;
import com.fiap.lanchonete.domain.service.AlimentoServiceImpl;
import com.fiap.lanchonete.domain.service.ClienteServiceImpl;
import com.fiap.lanchonete.domain.service.HistoricoPedidoAlimentoServiceImpl;
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
            PedidoAlimentoRepository pedidoAlimentoRepository,
            PedidoMapper pedidoMapper,
            AlimentoService alimentoService,
            PedidoAlimentoMapper pedidoAlimentoMapper, HistoricoPedidoService historicoPedidoService,
            HistoricoPedidoAlimentoService historicoPedidoAlimentoService,
            MetodoPagamentoService metodoPagamentoService, WebhookService webhookService) {
        return new PedidoServiceImpl(pedidoRepository, pedidoAlimentoRepository,
                pedidoMapper, alimentoService, pedidoAlimentoMapper, historicoPedidoService,
                historicoPedidoAlimentoService, metodoPagamentoService);
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
