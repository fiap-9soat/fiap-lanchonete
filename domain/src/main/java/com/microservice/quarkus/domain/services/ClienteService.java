package com.microservice.quarkus.domain.services;

import com.microservice.quarkus.domain.model.Cliente;
import com.microservice.quarkus.domain.ports.out.ClienteRepository;

public class ClienteService {
    ClienteRepository clienteRepository;

    public Long cadastrarCliente(String cpf, String nome, String sobrenome, String email) {
        return clienteRepository.cadastrarCliente(cpf, nome, sobrenome, email);
    }

    public Cliente consultarCliente(String cpf) {
        return clienteRepository.consultaCliente(cpf);
    }
}
