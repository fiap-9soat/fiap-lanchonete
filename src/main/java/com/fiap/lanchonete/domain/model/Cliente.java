package com.fiap.lanchonete.domain.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cliente {
    private Integer codigoCliente;
    private String cpf;
    private String nome;
    private String sobrenome;
    private String email;
}
