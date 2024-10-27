package com.fiap.lanchonete.infrastructure.mysql.entity;

import com.fiap.lanchonete.domain.model.EstadoPedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "Hist_Pedidos")
@IdClass(HistoricoPedidoId.class)
public class HistoricoPedidoEntity {

    @Id
    @NotNull
    @Column(name = "codigo_pedido", nullable = false)
    private Integer codigoPedido;

    @Id
    @NotNull
    @Column(name = "codigo_cliente", nullable = false)
    private Integer codigoCliente;

    @Id
    @NotNull
    @Column(name = "ts_alter", nullable = false)
    private Instant tsAlter;

    @NotNull
    @Column(name = "ts_ultimo_pedido", nullable = false)
    private Instant tsUltimoPedido;

    @NotNull
    @Column(name = "estado_pedido", nullable = false)
    private EstadoPedido estadoPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_pedido", referencedColumnName = "codigo_pedido")
    private PedidoEntity pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_cliente", referencedColumnName = "codigo_cliente")
    private ClienteEntity cliente;
}
