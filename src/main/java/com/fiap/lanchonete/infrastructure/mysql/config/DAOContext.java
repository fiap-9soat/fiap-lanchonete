package com.fiap.lanchonete.infrastructure.mysql.config;

import com.fiap.lanchonete.domain.ports.out.AlimentoRepository;
import com.fiap.lanchonete.domain.ports.out.ClienteRepository;
import com.fiap.lanchonete.infrastructure.mysql.adapter.out.AlimentoRepositoryImpl;
import com.fiap.lanchonete.infrastructure.mysql.adapter.out.ClienteRepositoryImpl;
import com.fiap.lanchonete.infrastructure.mysql.dao.AlimentoPanacheRepository;
import com.fiap.lanchonete.infrastructure.mysql.dao.ClientePanacheRepository;
import com.fiap.lanchonete.infrastructure.mysql.mapper.AlimentoMapper;
import com.fiap.lanchonete.infrastructure.mysql.mapper.ClienteMapper;
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
            ClienteMapper clienteMapper) {

        return new ClienteRepositoryImpl(clientePanacheRepository, clienteMapper);
    }

    @Produces
    public AlimentoRepository alimentoRepository(AlimentoPanacheRepository alimentoPanacheRepository,
            AlimentoMapper alimentoMapper) {
        return new AlimentoRepositoryImpl(alimentoPanacheRepository, alimentoMapper);
    }

}
