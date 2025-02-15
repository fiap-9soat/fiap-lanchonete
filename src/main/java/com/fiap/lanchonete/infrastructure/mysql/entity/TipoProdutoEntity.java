package com.fiap.lanchonete.infrastructure.mysql.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Produtos_Tipo")
@IdClass(TipoProdutoEntityId.class)
public class TipoProdutoEntity {
    @Id
    @Column(name = "codigo_tipo_produto")
    private Short codigoTipoProduto;

    @Id
    @Column(name = "nome_tipo_produto")
    private String nomeTipoProduto;
}
