package com.fiap.lanchonete.infrastructure.mysql.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fiap.lanchonete.domain.enums.EstadoPedido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Pedidos")
@Getter
@Setter
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_pedido")
    private Integer codigoPedido;

    @Column(name = "codigo_cliente", nullable = true)
    private Integer codigoCliente;

    @Column(nullable = false, columnDefinition = "TIMESTAMP", name = "ts_ultimo_pedido")
    @CreationTimestamp
    private LocalDateTime tsUltimoPedido;

    @Column(nullable = false, name = "estado_pedido")
    private EstadoPedido estadoPedido;

    @OneToMany
    @JoinColumn(name = "codigo_pedido", referencedColumnName = "codigo_pedido", insertable = false, updatable = false)
    private Set<PedidoAlimentoEntity> pedidoAlimento = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "codigo_cliente", referencedColumnName = "codigo_cliente", insertable = false, updatable = false)
    private ClienteEntity cliente;
}
