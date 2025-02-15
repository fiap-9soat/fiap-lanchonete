package com.fiap.lanchonete.infrastructure.mysql.entity;

import java.time.LocalDateTime;

import com.fiap.lanchonete.domain.enums.TipoAlteracao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Hist_Pedidos_Produtos")
@IdClass(HistoricoPedidoProdutoEntityId.class)
public class HistoricoPedidoProdutoEntity {

    @Id
    @Column(name = "codigo_pedido")
    private Integer codigoPedido;

    @Id
    @Column(name = "codigo_tipo_produto")
    private Short codigoTipoProduto;

    @Id
    @Column(name = "codigo_produto")
    private Short codigoProduto;

    @Id
    @Column(name = "ts_alter")
    private LocalDateTime tsalter;

    @Column(name = "qtdade_produto")
    private Short qtdadeProduto;

    @Column(name = "tipo_alter")
    @Enumerated(EnumType.STRING)
    private TipoAlteracao tipoAlter;
}
