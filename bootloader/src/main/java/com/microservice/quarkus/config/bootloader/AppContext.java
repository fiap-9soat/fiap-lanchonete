package com.microservice.quarkus.config.bootloader;

import com.microservice.quarkus.application.out.ClienteService;
import com.microservice.quarkus.application.service.ClienteServiceImpl;
import com.microservice.quarkus.domain.model.ClienteFactory;
import com.microservice.quarkus.domain.ports.out.ClienteRepository;

import jakarta.enterprise.inject.Produces;

public class AppContext {
    @Produces
    public ClienteFactory cliente(ClienteRepository clienteRepository) {
        return new ClienteFactory(clienteRepository);
    }

    @Produces
    public ClienteService loanAPIService(ClienteRepository clienteRepository, ClienteFactory clienteFactory) {
        return new ClienteServiceImpl(clienteRepository, clienteFactory);
    }
}
