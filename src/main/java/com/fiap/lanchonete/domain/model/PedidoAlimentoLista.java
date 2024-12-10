package com.fiap.lanchonete.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class PedidoAlimentoLista {
    Short codigoTipoAlimento;
    Short codigoAlimento;
    Short quantidadeAlimento;
    /**
     * quantidadeAlimento * precoAlimento
     */
    BigDecimal valorTotal;
}
