package com.fiap.lanchonete.domain.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaItemExternoDto {
    private String titulo;
    private String categoria;
    private Double valorUnitario;
    private Integer quantidade;
    private String medida;
    private Double valorAgregado;
}
