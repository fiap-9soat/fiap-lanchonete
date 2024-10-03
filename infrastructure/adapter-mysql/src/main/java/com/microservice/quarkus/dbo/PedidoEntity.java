package com.microservice.quarkus.dbo;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    @Column(name = "codigo_pedido")
    private Integer codigoPedido;

    @Id
    @Column(name = "codigo_cliente")
    private Integer codigoCliente;

    @Id
    @Column(name = "ts_pedido")
    private LocalDateTime tsPedido;

    @OneToMany
    @JoinColumn(name = "codigo_alimento", referencedColumnName = "codigo_alimento")
    @JoinColumn(name = "codigo_tipo_alimento", referencedColumnName = "codigo_tipo_alimento")
    private Set<AlimentoEntity> alimentos = new HashSet<>();
}
