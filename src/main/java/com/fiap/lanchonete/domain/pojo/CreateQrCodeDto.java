package com.fiap.lanchonete.domain.pojo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQrCodeDto {
    private String identificadorExterno;
    private String titulo;
    private String descricao;
    private String urlNotificacao;
    private Integer totalPedido;
    private List<ListaItemExternoDto> listaItems;
}
