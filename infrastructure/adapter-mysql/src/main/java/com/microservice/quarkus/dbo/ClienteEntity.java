package com.microservice.quarkus.dbo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Clientes")
@Getter
@Setter
public class ClienteEntity {
    @Id
    @Column(name = "codigo_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoCliente;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "email")
    private String email;
}
