package com.fiap.lanchonete.infrastructure.config;

import com.fiap.lanchonete.domain.ports.in.ClienteService;
import com.fiap.lanchonete.domain.ports.in.EstadoPedidoListener;
import com.fiap.lanchonete.domain.ports.in.PedidoService;
import com.fiap.lanchonete.domain.ports.out.ClienteRepository;
import com.fiap.lanchonete.domain.ports.out.PedidoRepository;
import com.fiap.lanchonete.domain.service.ClienteServiceImpl;
import com.fiap.lanchonete.domain.service.EstadoPedidoListenerImpl;
import com.fiap.lanchonete.domain.service.PedidoServiceImpl;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;

/**
 * Classe responsável por "produzir" beans relacionados a services para injeção de dependencia.
 */
@Dependent
public class ServiceContext {

    @Produces
    public ClienteService clienteService(ClienteRepository clienteRepository) {
        return new ClienteServiceImpl(clienteRepository);
    }

    @Produces
    public PedidoService pedidoService(PedidoRepository pedidoRepository) {
        return new PedidoServiceImpl(pedidoRepository);
    }

    @Produces
    public EstadoPedidoListener EstadoPedidoListener(PedidoService pedidoService){
        return new EstadoPedidoListenerImpl(pedidoService);
    }

}
