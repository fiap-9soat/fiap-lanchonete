package com.fiap.lanchonete.infrastructure.mysql.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Alimentos_Tipo")
@IdClass(TipoAlimentoEntityId.class)
public class TipoAlimentoEntity {
    @Id
    @Column(name = "codigo_tipo_alimento")
    private Short codigoTipoAlimento;

    @Id
    @Column(name = "nome_tipo_alimento")
    private String nomeTipoAlimento;
}
