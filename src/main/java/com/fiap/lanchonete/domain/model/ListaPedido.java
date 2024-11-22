package com.fiap.lanchonete.domain.model;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListaPedido {
    Integer codigoPedido;
    Integer estadoPedido;
    Instant tsUltimoPedido;
    List<PedidoAlimentoLista> listaPedidos;
}
