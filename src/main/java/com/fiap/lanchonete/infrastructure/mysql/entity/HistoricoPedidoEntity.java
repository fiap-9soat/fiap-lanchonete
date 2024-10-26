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
public class HistoricoPedidoEntity {
    @EmbeddedId
    private HistoricoPedidoId id;

    @NotNull
    @Column(name = "ts_ultimo_pedido", nullable = false)
    private Instant tsUltimoPedido;

    @MapsId("codigoCliente")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codigo_cliente", nullable = false)
    private ClienteEntity codigoCliente;

    @NotNull
    @Column(name = "estado_pedido", nullable = false)
    private EstadoPedido estadoPedido;
}
