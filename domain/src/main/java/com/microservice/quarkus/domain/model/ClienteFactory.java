package com.microservice.quarkus.domain.model;

import com.microservice.quarkus.domain.ports.out.ClienteRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class ClienteFactory {
    
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(Cliente cliente) {
        return cadastrarCliente(cliente.getCpf(), cliente.getNome(), cliente.getSobrenome(), cliente.getEmail());
    }

    public Cliente cadastrarCliente(String cpf, String nome, String sobrenome, String email) {
        log.debug("Cadastrando Cliente: {}, {}, {}, {}", cpf, nome, sobrenome, email);
        
        Long idCliente =  clienteRepository.cadastrarCliente(cpf, nome, sobrenome, email);

        Cliente cliente = Cliente.builder()
            .codigoCliente(idCliente)
            .cpf(cpf)
            .nome(nome)
            .sobrenome(sobrenome)
            .email(email)
            .build();
        

        cliente.setCodigoCliente(idCliente);
        return cliente;
    }
}
