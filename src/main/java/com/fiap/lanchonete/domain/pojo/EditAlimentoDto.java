package com.fiap.lanchonete.domain.pojo;

import java.math.BigDecimal;

public record EditAlimentoDto(Short codigoTipoAlimento, Short codigoAlimento, String nomeAlimento,
                              String nomeFunciAlter, BigDecimal precoAlimento) {
}
