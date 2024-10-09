package com.microservice.quarkus.application.out;

import com.microservice.quarkus.application.dto.ClienteDTO;

public interface ClienteService {

    public void cadastra(String cpf, ClienteDTO cliente);

}
