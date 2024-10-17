package com.fiap.lanchonete.domain.model;

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
public class TipoAlimento {
    private Integer codigoTipoAlimento;
    private String nomeTipoAlimento;
}
