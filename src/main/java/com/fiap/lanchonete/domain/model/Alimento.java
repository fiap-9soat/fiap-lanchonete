package com.fiap.lanchonete.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

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