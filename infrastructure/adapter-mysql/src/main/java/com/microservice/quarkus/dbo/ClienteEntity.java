package com.microservice.quarkus.dbo;

import com.microservice.quarkus.domain.shared.ClienteAbstract;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Clientes")
public class ClienteEntity extends ClienteAbstract {
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
