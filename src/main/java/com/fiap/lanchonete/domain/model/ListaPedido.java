package com.fiap.lanchonete.domain.model;

import java.time.Instant;
import java.util.List;

import com.fiap.lanchonete.domain.enums.EstadoPagamento;
import com.fiap.lanchonete.domain.enums.EstadoPedido;

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
    EstadoPedido estadoPedido;
    EstadoPagamento estadoPagamento;
    Instant tsUltimoPedido;
    List<PedidoAlimentoLista> listaPedidos;
}
