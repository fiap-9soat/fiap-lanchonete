package com.fiap.lanchonete.infrastructure.mysql.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Alimentos")
@IdClass(AlimentoEntityId.class)
@Getter
@Setter
public class AlimentoEntity {

    @Id
    @Column(name = "codigo_tipo_alimento")
    private Short codigoTipoAlimento;

    @Id
    @Column(name = "codigo_alimento")
    private Short codigoAlimento;

    @Column(name = "nome_alimento")
    private String nomeAlimento;

    @Column(name = "nome_funci_alter")
    private String nomeFunciAlter;

    @Column(name = "preco_alimento", columnDefinition = "DECIMAL(16, 2) DEFAULT 0.0")
    private BigDecimal precoAlimento;

    @Column(name = "ts_alter")
    private LocalDateTime tsAlter;
}
