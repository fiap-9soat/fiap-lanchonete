package com.fiap.lanchonete.infrastructure.mysql.entity;

import com.fiap.lanchonete.domain.model.EstadoPedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "Pedidos")
@Getter
@Setter
@IdClass(PedidoEntityId.class)
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_pedido")
    private Integer codigoPedido;
    @Id
    @Column(name = "codigo_cliente")
    private Integer codigoCliente;

    @Column(nullable = false, columnDefinition = "TIMESTAMP", name = "ts_pedido")
    @CreationTimestamp
    private Instant tsPedido;

    @Column(nullable = false, name = "estado_pedido")
    private EstadoPedido estadoPedido;

    // TODO: Descomentar quando implementar o AlimentoEntity
    //    @Column(nullable = false, name = "codigo_tipo_alimento")
    //    private Short codigoTipoAlimento;
    //    @Column(nullable = false, name = "codigo_alimento")
    //    private Short codigoAlimento;


    @ManyToOne
    @JoinColumn(name="codigo_cliente", referencedColumnName = "codigo_cliente")
    private ClienteEntity cliente;

}
