package com.fiap.lanchonete.infrastructure.mysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class HistoricoPedidoId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = -4413322933286485451L;

    @NotNull
    @Column(name = "codigo_pedido", nullable = false)
    private Integer codigoPedido;

    @NotNull
    @Column(name = "codigo_cliente", nullable = false)
    private Integer codigoCliente;

    @NotNull
    @Column(name = "ts_pedido", nullable = false)
    private Instant tsPedido;

    @NotNull
    @Column(name = "ts_alter", nullable = false)
    private Instant tsAlter;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HistoricoPedidoId entity = (HistoricoPedidoId) o;
        return Objects.equals(this.tsAlter, entity.tsAlter) &&
                Objects.equals(this.codigoPedido, entity.codigoPedido) &&
                Objects.equals(this.tsPedido, entity.tsPedido) &&
                Objects.equals(this.codigoCliente, entity.codigoCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tsAlter, codigoPedido, tsPedido, codigoCliente);
    }

}