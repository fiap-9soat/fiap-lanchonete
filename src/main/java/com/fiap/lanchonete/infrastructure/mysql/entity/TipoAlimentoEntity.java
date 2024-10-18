package com.fiap.lanchonete.infrastructure.mysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Alimentos_Tipo")
public class TipoAlimentoEntity {
    @Id
    @Column(name = "codigo_tipo_alimento")
    private Short codigoTipoAlimento;

    @Id
    @Column(name = "nome_tipo_alimento")
    private String nomeTipoAlimento;
}
