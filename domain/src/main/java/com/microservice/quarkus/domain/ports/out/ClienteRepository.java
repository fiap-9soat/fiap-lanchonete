package com.microservice.quarkus.domain.ports.out;

import com.microservice.quarkus.domain.model.Cliente;

public interface ClienteRepository {

    Cliente consultaCliente(String cpf);

    Long cadastrarCliente(String cpf, String nome, String sobrenome, String email);
}
