package com.microservice.quarkus.application.service;

import com.microservice.quarkus.application.dto.ClienteDTO;
import com.microservice.quarkus.application.out.ClienteService;
import com.microservice.quarkus.domain.model.ClienteFactory;
import com.microservice.quarkus.domain.ports.out.ClienteRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class ClienteServiceImpl implements ClienteService {

    ClienteRepository clienteRepository;

    public void cadastra(String cpf, ClienteDTO cliente) {
        clienteRepository.cadastrarCliente(cpf, cliente.nome(),
                cliente.sobrenome(), cliente.email());
    }

}
