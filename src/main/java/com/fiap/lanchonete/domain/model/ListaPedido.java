package com.fiap.lanchonete.domain.model;

import java.time.Instant;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ListaPedido {
    Integer codigoPedido;
    Instant tsUltimoPedido;
    List<PedidoAlimentoLista> listaPedidos;
}
