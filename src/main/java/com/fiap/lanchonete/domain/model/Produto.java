package com.fiap.lanchonete.domain.model;

import java.math.BigDecimal;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Produto {
    private Short codigoTipoProduto;
    private Short codigoProduto;
    private String nomeProduto;
    private String nomeFunciAlter;
    private BigDecimal precoProduto;
    private Instant tsAlter;
}
