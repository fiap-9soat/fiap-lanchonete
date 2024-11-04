package com.fiap.lanchonete.infrastructure.mysql.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TipoAlimentoEntityId implements Serializable {
    private Short codigoTipoAlimento;
    private String nomeTipoAlimento;
}
