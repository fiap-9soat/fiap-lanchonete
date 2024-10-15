package com.fiap.lanchonete.infrastructure.mysql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Clientes")
@Getter
@Setter
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_cliente")
    private Integer codigoCliente;
    @Column(nullable = false, name = "cpf", length = 11)
    private String cpf;
    @Column(nullable = false, name = "nome", length = 14)
    private String nome;
    @Column(nullable = false, name = "sobrenome", length = 16)
    private String sobrenome;
    @Column(nullable = false, name = "email", length = 30)
    private String email;

}
