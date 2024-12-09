package com.fiap.lanchonete.domain.pojo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaItemExternoDto {
    private String titulo;
    private String categoria;
    private BigDecimal valorUnitario;
    private Integer quantidade;
    private String medida;
    private BigDecimal valorAgregado;
}
