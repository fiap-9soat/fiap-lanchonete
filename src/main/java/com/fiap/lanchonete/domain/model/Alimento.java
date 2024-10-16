package com.fiap.lanchonete.domain.model;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Alimento {
    private Integer codigoTipoAlimento;
    private Integer codigoAlimento;
    private String nomeTipoAlimento;
    private String nomeAlimento;
    private String nomeFunciAlter;
    private Instant tsAlter;
}
