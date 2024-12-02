package com.fiap.lanchonete.domain.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlimentoDto {
    private Short codigoTipoAlimento;
    private Short codigoAlimento;
    private Integer quantidadeAlimento;
}
