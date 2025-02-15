package com.fiap.lanchonete.infrastructure.mysql.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Produtos")
@IdClass(ProdutoEntityId.class)
@Getter
@Setter
public class ProdutoEntity {

    @Id
    @Column(name = "codigo_tipo_produto")
    private Short codigoTipoProduto;

    @Id
    @Column(name = "codigo_produto")
    private Short codigoProduto;

    @Column(name = "nome_produto")
    private String nomeProduto;

    @Column(name = "nome_funci_alter")
    private String nomeFunciAlter;

    @Column(name = "preco_produto", columnDefinition = "DECIMAL(16, 2) DEFAULT 0.0")
    private BigDecimal precoProduto;

    @Column(name = "ts_alter")
    private LocalDateTime tsAlter;
}
