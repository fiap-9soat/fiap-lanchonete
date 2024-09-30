package com.microservice.quarkus.domain.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Cliente {
    private Long codigoCliente;
    private String cpf;
    private String nome;
    private String sobrenome;
    private String email;
}
