package com.fiap.lanchonete.infrastructure.config.context;

import com.fiap.lanchonete.domain.mapper.AlimentoMapper;
import com.fiap.lanchonete.domain.mapper.ClienteMapper;
import com.fiap.lanchonete.domain.mapper.PedidoAlimentoMapper;
import com.fiap.lanchonete.domain.mapper.PedidoMapper;
import com.fiap.lanchonete.domain.ports.in.AlimentoService;
import com.fiap.lanchonete.domain.ports.in.ClienteService;
import com.fiap.lanchonete.domain.ports.in.EstadoPedidoListener;
import com.fiap.lanchonete.domain.ports.in.PedidoService;
import com.fiap.lanchonete.domain.ports.out.AlimentoRepository;
import com.fiap.lanchonete.domain.ports.out.ClienteRepository;
import com.fiap.lanchonete.domain.ports.out.PedidoAlimentoRepository;
import com.fiap.lanchonete.domain.ports.out.PedidoRepository;
import com.fiap.lanchonete.domain.service.AlimentoServiceImpl;
import com.fiap.lanchonete.domain.service.ClienteServiceImpl;
import com.fiap.lanchonete.domain.service.EstadoPedidoListenerImpl;
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
            PedidoAlimentoMapper pedidoAlimentoMapper) {
        return new PedidoServiceImpl(pedidoRepository, pedidoAlimentoRepository,
                pedidoMapper, pedidoAlimentoMapper);
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

}
