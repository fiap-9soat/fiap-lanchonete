package com.fiap.lanchonete.application.dto;

import java.time.LocalDateTime;

public record CreateAlimentoDto(
                Integer codigoTipoAlimento,
                Integer codigoAlimento,
                String nomeTipoAlimento,
                String nomeAlimento,
                String nomeFunciAlter,
                LocalDateTime tsAlter) {
}
