package com.microservice.quarkus.domain.model;
import com.microservice.quarkus.domain.ports.out.ClienteRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
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
