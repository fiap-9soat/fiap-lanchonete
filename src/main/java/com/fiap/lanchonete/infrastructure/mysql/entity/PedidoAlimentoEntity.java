package com.fiap.lanchonete.infrastructure.mysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Pedidos_Alimentos")
@Getter
@Setter
@IdClass(PedidoAlimentoEntityId.class)
public class PedidoAlimentoEntity {

    @Id
    @Column(name = "codigo_pedido")
    private Integer codigoPedido;

    @Id
    @Column(name = "codigo_tipo_alimento")
    private Short codigoTipoAlimento;

    @Id
    @Column(name = "codigo_alimento")
    private Short codigoAlimento;

    @Column(name = "qtdade_alimento")
    private Short qtdadeAlimento;
}
