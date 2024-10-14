package com.microservice.quarkus.dbo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="Hist_Pedidos")
public class HistoricPedidoEntity {
    @Id
    @Column(name = "codigo_pedido")
    private Integer codigoPedido;

    @Id
    @Column(name = "codigo_cliente")
    private Integer codigoCliente;

    @Id
    @Column(name = "ts_pedido")
    private LocalDateTime tsPedido;

    @Id
    @Column(name = "ts_alter")
    private LocalDateTime tsAlter;

    @OneToOne
    @JoinColumn(name = "codigo_alimento", referencedColumnName = "codigo_alimento")
    @JoinColumn(name = "codigo_tipo_alimento", referencedColumnName = "codigo_tipo_alimento")
    private AlimentoEntity alimento;
}
