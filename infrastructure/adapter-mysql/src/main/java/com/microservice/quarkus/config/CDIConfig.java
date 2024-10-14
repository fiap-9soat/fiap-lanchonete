package com.microservice.quarkus.config;

import com.microservice.quarkus.domain.ports.out.ClienteRepository;
import com.microservice.quarkus.mapper.ClienteMapper;
import com.microservice.quarkus.repository.ClienteRepositoryImpl;
import com.microservice.quarkus.repository.ClienteRepositoryPanache;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;

@Dependent
public class CDIConfig {

    @Produces
    public ClienteRepository clienteRepository(ClienteRepositoryPanache clienteRepositoryPanache, ClienteMapper clienteMapper) {
        return new ClienteRepositoryImpl(clienteRepositoryPanache, clienteMapper);
    }


}
