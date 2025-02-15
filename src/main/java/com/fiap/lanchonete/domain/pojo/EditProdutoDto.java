package com.fiap.lanchonete.domain.pojo;

import java.math.BigDecimal;

public record EditProdutoDto(Short codigoTipoProduto, Short codigoProduto, String nomeProduto,
        String nomeFunciAlter, BigDecimal precoProduto) {
}
