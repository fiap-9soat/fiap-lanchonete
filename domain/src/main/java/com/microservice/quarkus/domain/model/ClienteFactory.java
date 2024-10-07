package com.microservice.quarkus.domain.model;

import com.microservice.quarkus.domain.ports.out.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClienteFactory {
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(Cliente cliente) {
        return cadastrarCliente(cliente.getCpf(), cliente.getNome(), cliente.getSobrenome(), cliente.getEmail());
    }

    public Cliente cadastrarCliente(String cpf, String nome, String sobrenome, String email) {
        
    }
}
