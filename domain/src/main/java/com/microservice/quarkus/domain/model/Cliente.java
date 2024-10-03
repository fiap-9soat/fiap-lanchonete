package com.microservice.quarkus.domain.model;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Cliente {
    private Long codigoCliente;
    private String cpf;
    private String nome;
    private String sobrenome;
    private String email;
}
