package com.fiap.lanchonete.infrastructure.config.context;

import com.fiap.lanchonete.domain.ports.out.AlimentoRepository;
import com.fiap.lanchonete.domain.ports.out.ClienteRepository;
import com.fiap.lanchonete.domain.ports.out.PedidoRepository;
import com.fiap.lanchonete.infrastructure.mysql.adapter.out.AlimentoRepositoryImpl;
import com.fiap.lanchonete.infrastructure.mysql.adapter.out.ClienteRepositoryImpl;
import com.fiap.lanchonete.infrastructure.mysql.adapter.out.PedidoRepositoryImpl;
import com.fiap.lanchonete.infrastructure.mysql.dao.AlimentoPanacheRepository;
import com.fiap.lanchonete.infrastructure.mysql.dao.ClientePanacheRepository;
import com.fiap.lanchonete.infrastructure.mysql.mapper.AlimentoEntityMapper;
import com.fiap.lanchonete.infrastructure.mysql.mapper.ClienteEntityMapper;
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
    public PedidoRepository PedidoRepository() {
        return new PedidoRepositoryImpl();
    }

    @Produces
    public AlimentoRepository alimentoRepository(AlimentoPanacheRepository alimentoPanacheRepository,
                                                 AlimentoEntityMapper alimentoEntityMapper) {
        return new AlimentoRepositoryImpl(alimentoPanacheRepository, alimentoEntityMapper);
    }

}
