package com.fiap.lanchonete.domain.pojo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoExternoDto {
    private String idPedidoExterno;
    private String titulo;
    private String descricao;
    private String urlNotificacao;
    private BigDecimal total;
    private List<ListaItemExternoDto> items;
}
