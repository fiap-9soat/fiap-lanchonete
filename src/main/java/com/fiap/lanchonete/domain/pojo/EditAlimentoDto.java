package com.fiap.lanchonete.domain.pojo;

public record EditAlimentoDto(
    Integer codigoTipoAlimento,
    Integer codigoAlimento,
    String nomeAlimento,
    String nomeFunciAlter) {
}
