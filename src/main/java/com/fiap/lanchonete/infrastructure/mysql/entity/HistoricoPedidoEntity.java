package com.fiap.lanchonete.infrastructure.mysql.entity;

import java.time.LocalDateTime;

import com.fiap.lanchonete.domain.enums.EstadoPedido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Hist_Pedidos")
@IdClass(HistoricoPedidoId.class)
public class HistoricoPedidoEntity {

    @Id
    @Column(name = "codigo_pedido")
    private Integer codigoPedido;

    @Id
    @Column(name = "ts_alter")
    private LocalDateTime tsAlter;

    @Column(name = "codigo_cliente")
    private Integer codigoCliente;

    @Column(name = "ts_ultimo_pedido")
    private LocalDateTime tsUltimoPedido;

    @Column(name = "estado_pedido")
    private EstadoPedido estadoPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @Transient
    @JoinColumn(name = "codigo_pedido", referencedColumnName = "codigo_pedido")
    private PedidoEntity pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @Transient
    @JoinColumn(name = "codigo_cliente", referencedColumnName = "codigo_cliente")
    private ClienteEntity cliente;
}
