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

import java.math.BigDecimal;

@Entity
@Table(name = "Pedidos_Alimentos")
@Getter
@Setter
@IdClass(PedidoAlimentoEntityId.class)
public class PedidoAlimentoEntity {

    @Id
    @Column(name = "codigo_pedido")
    private Integer codigoPedido;

    @Id
    @Column(name = "codigo_tipo_alimento")
    private Short codigoTipoAlimento;

    @Id
    @Column(name = "codigo_alimento")
    private Short codigoAlimento;

    @Column(name = "qtdade_alimento")
    private Short quantidadeAlimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_tipo_alimento", referencedColumnName = "codigo_tipo_alimento", insertable = false, updatable = false)
    @JoinColumn(name = "codigo_alimento", referencedColumnName = "codigo_alimento", insertable = false, updatable = false)
    private AlimentoEntity alimento;
}
