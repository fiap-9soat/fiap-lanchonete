package com.fiap.lanchonete.application.dto;

public record CreateAlimentoDto(
        Integer codigoTipoAlimento,
        String nomeAlimento,
        String nomeFunciAlter) {
}
