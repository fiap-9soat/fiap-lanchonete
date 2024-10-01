package com.microservice.quarkus.dbo;

import com.microservice.quarkus.domain.model.Alimento;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "Alimentos")
@IdClass(AlimentoEntityId.class)
public class AlimentoEntity extends Alimento {
    @Id
    private Short codigoTipoAlimento;
    @Id
    private Short codigoAlimento;

    @UpdateTimestamp
    private Instant tsAlter;
}
