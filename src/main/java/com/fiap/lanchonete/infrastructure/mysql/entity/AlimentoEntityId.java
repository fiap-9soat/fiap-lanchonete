package com.fiap.lanchonete.infrastructure.mysql.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlimentoEntityId implements Serializable {
    private Short codigoTipoAlimento;
    private Short codigoAlimento;
}