package com.fiap.lanchonete.infrastructure.mysql.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AlimentoEntityId implements Serializable {
    private Short codigoTipoAlimento;
    private Short codigoAlimento;
}
