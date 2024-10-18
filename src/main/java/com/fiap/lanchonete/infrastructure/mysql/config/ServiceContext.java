package com.fiap.lanchonete.infrastructure.mysql.config;

import com.fiap.lanchonete.domain.ports.in.AlimentoService;
import com.fiap.lanchonete.domain.ports.in.ClienteService;
import com.fiap.lanchonete.domain.ports.out.AlimentoRepository;
import com.fiap.lanchonete.domain.ports.out.ClienteRepository;
import com.fiap.lanchonete.domain.service.AlimentoServiceImpl;
import com.fiap.lanchonete.domain.service.ClienteServiceImpl;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;

/**
 * Classe responsável por "produzir" beans relacionados a services para injeção
 * de dependencia.
 */
@Dependent
public class ServiceContext {

    @Produces
    public ClienteService clienteService(ClienteRepository clienteRepository) {
        return new ClienteServiceImpl(clienteRepository);
    }

    @Produces
    public AlimentoService alimentoService(AlimentoRepository alimentoRepository) {
        return new AlimentoServiceImpl(alimentoRepository);
    }

}
