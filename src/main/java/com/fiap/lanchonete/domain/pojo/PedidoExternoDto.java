package com.fiap.lanchonete.domain.pojo;

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
    private Double total;
    private List<ListaItemExternoDto> items;
}
