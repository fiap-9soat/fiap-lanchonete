package com.fiap.lanchonete.infrastructure.config;

import com.fiap.lanchonete.domain.ports.out.ClienteRepository;
import com.fiap.lanchonete.domain.ports.out.PedidoRepository;
import com.fiap.lanchonete.infrastructure.mysql.adapter.out.ClienteRepositoryImpl;
import com.fiap.lanchonete.infrastructure.mysql.adapter.out.PedidoRepositoryImpl;
import com.fiap.lanchonete.infrastructure.mysql.dao.ClientePanacheRepository;
import com.fiap.lanchonete.infrastructure.mysql.dao.PedidoPanacheRepository;
import com.fiap.lanchonete.infrastructure.mysql.mapper.ClienteMapper;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;

/**
 * Classe responsável por "produzir" beans relacionados a DAOs (ex.: repositorios) para injeção de dependencia.
 */
@Dependent
public class DAOContext {

    @Produces
    public ClienteRepository clienteRepository(ClientePanacheRepository clientePanacheRepository,
                                               ClienteMapper clienteMapper) {

        return new ClienteRepositoryImpl(clientePanacheRepository, clienteMapper);
    }

    @Produces
    public PedidoRepository PedidoRepository(){
        return new PedidoRepositoryImpl();
    }

}
