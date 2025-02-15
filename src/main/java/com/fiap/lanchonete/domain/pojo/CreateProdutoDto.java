package com.fiap.lanchonete.domain.pojo;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public record CreateProdutoDto(@NotEmpty Short codigoTipoProduto, @NotEmpty String nomeProduto,
        @NotEmpty String nomeFunciAlter,
        @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero") BigDecimal precoProduto) {
}
