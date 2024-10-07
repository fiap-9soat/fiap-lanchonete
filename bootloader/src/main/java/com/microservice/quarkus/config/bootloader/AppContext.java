package com.microservices.quarkus.config.bootloader;

import jakarta.enterprise.inject.Produces;
import com.microservice.quarkus.domain.model.Cliente;
import com.microservice.quarkus.domain.ports.out.ClienteRepository;

public class AppContext {
    @Produces
    public Cliente cliente(ClienteRepository clienteRepository) {
        return new Cliente(clienteRepository);
    }

    // @Produces
    // public LoanAPIService loanAPIService(LoanRepository loanRepository, EventBus eventBus, LoanFactory loanFactory) {        
    //     return new LoanAPIServiceImpl(loanRepository, loanFactory, eventBus);
    // }
}
