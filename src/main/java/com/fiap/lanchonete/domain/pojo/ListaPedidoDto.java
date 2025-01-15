package com.fiap.lanchonete.domain.pojo;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.fiap.lanchonete.domain.enums.EstadoPagamento;
import com.fiap.lanchonete.domain.enums.EstadoPedido;

import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListaPedidoDto {
    Integer codigoPedido;
    EstadoPedido estadoPedido;
    EstadoPagamento estadoPagamento;
    Instant tsUltimoPedido;
    BigDecimal valorTotal;
    List<ListaPedidoAlimentoDto> listaPedidoAlimentos;
}
