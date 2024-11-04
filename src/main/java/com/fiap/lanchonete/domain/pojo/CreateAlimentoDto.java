package com.fiap.lanchonete.domain.pojo;

import jakarta.validation.constraints.NotEmpty;

public record CreateAlimentoDto(
    @NotEmpty Short codigoTipoAlimento,
    @NotEmpty String nomeAlimento,
    @NotEmpty String nomeFunciAlter) {
}
