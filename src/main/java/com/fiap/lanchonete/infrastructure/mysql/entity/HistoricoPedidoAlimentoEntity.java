package com.fiap.lanchonete.infrastructure.mysql.entity;

import java.time.LocalDateTime;

import com.fiap.lanchonete.domain.enums.TipoAlteracao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Hist_Pedidos_Alimentos")
@IdClass(HistoricoPedidoAlimentoEntityId.class)
public class HistoricoPedidoAlimentoEntity {

    @Id
    @Column(name = "codigo_pedido")
    private Integer codigoPedido;

    @Id
    @Column(name = "codigo_tipo_alimento")
    private Short codigoTipoAlimento;

    @Id
    @Column(name = "codigo_alimento")
    private Short codigoAlimento;

    @Id
    @Column(name = "ts_alter")
    private LocalDateTime tsalter;

    @Column(name = "qtdade_alimento")
    private Integer qtdadeAlimento;

    @Column(name = "tipo_alter")
    @Enumerated(EnumType.STRING)
    private TipoAlteracao tipoAlter;
}
