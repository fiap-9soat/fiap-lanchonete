package com.microservice.quarkus.domain.shared;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ClienteAbstract {
    private Long codigoCliente;
    private String cpf;
    private String nome;
    private String sobrenome;
    private String email;
}
