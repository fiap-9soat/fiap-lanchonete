package com.fiap.lanchonete.domain.pojo;

public record EditAlimentoDto(
    Short codigoTipoAlimento,
    Short codigoAlimento,
    String nomeAlimento,
    String nomeFunciAlter) {
}
