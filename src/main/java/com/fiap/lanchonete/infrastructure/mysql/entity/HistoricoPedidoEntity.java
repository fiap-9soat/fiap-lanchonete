package com.fiap.lanchonete.infrastructure.mysql.entity;

import com.fiap.lanchonete.domain.model.EstadoPedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Hist_Pedidos")
public class HistoricoPedidoEntity {
    @EmbeddedId
    private HistoricoPedidoId id;

    @MapsId("codigoCliente")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codigo_cliente", nullable = false)
    private ClienteEntity codigoCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "codigo_tipo_alimento", referencedColumnName = "codigo_tipo_alimento"),
            @JoinColumn(name = "codigo_alimento", referencedColumnName = "codigo_alimento")
    })
    private AlimentoEntity alimentos;

    @NotNull
    @Column(name = "estado_pedido", nullable = false)
    private EstadoPedido estadoPedido;

    @NotNull
    @Column(name = "qtdade_alimentos", nullable = false)
    private Short qtdadeAlimentos;

}