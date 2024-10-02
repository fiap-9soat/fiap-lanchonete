package com.microservice.quarkus.dbo;

import java.time.LocalDateTime;

import com.microservice.quarkus.domain.shared.AlimentoAbstract;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "Alimentos")
@IdClass(AlimentoEntityId.class)
public class AlimentoEntity extends AlimentoAbstract {

    @Id
    @Column(name = "codigo_tipo_alimento")
    private Short codigoTipoAlimento;

    @Id
    @Column(name = "codigo_alimento")
    private Short codigoAlimento;

    @Column(name = "nome_tipo_alimento")
    private String nomeTipoAlimento;

    @Column(name = "nome_alimento")
    private String nomeAlimento;

    @Column(name = "nome_funci_alter")
    private String nomeFunci;

    @Column(name = "ts_alter")
    private LocalDateTime tsAlteracao;
}
    
