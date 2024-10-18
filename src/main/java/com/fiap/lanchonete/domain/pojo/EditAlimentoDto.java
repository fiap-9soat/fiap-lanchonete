package com.fiap.lanchonete.application.dto;

public record EditAlimentoDto(
                Integer codigoTipoAlimento,
                Integer codigoAlimento,
                String nomeAlimento,
                String nomeFunciAlter) {
}
