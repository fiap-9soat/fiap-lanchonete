package com.fiap.lanchonete.infrastructure.mysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Pedidos_Produtos")
@Getter
@Setter
@IdClass(PedidoProdutoEntityId.class)
public class PedidoProdutoEntity {

    @Id
    @Column(name = "codigo_pedido")
    private Integer codigoPedido;

    @Id
    @Column(name = "codigo_tipo_produto")
    private Short codigoTipoProduto;

    @Id
    @Column(name = "codigo_produto")
    private Short codigoProduto;

    @Column(name = "qtdade_produto")
    private Short quantidadeProduto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_tipo_produto", referencedColumnName = "codigo_tipo_produto", insertable = false, updatable = false)
    @JoinColumn(name = "codigo_produto", referencedColumnName = "codigo_produto", insertable = false, updatable = false)
    private ProdutoEntity produto;
}
