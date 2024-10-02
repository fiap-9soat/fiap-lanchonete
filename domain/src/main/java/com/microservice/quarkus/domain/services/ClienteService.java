package com.microservice.quarkus.domain.services;

import com.microservice.quarkus.domain.model.Cliente;
import com.microservice.quarkus.domain.ports.out.ClienteRepository;

public class ClienteService {
    ClienteRepository clienteRepository;

    public void cadastrarCliente(Cliente cliente) {
        clienteRepository.cadastrarCliente(cliente);
    }

    public Cliente consultarCliente(String cpf) {
        return clienteRepository.consultaCliente(cpf);
    }
}
