package com.fiap.lanchonete.domain.pojo;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateClienteDto(@NotBlank @Length(max = 14) String nome,
        @NotBlank @Length(max = 16) String sobrenome,
        @NotBlank @Length(max = 11) String cpf,
        @NotBlank @Length(max = 90) String email) {
}
