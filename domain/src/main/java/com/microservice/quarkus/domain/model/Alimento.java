package com.microservice.quarkus.domain.model;


import java.time.Instant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Alimento {
    private Short codigoTipoAlimento;
    private Short codigoAlimento;
    private String nomeTipoAlimento;
    private String nomeAlimento;
    private String nomeFunciAlter;
    private Instant tsAlter;
}
