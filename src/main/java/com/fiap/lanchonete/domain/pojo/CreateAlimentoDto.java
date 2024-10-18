package com.fiap.lanchonete.domain.pojo;

import jakarta.validation.constraints.NotEmpty;

public record CreateAlimentoDto(
    @NotEmpty Integer codigoTipoAlimento,
    @NotEmpty String nomeAlimento,
    @NotEmpty String nomeFunciAlter) {
}
