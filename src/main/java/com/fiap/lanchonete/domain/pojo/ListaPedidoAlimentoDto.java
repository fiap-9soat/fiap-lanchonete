package com.fiap.lanchonete.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ListaPedidoAlimentoDto {
    Short codigoTipoAlimento;
    Short codigoAlimento;
    Short quantidadeAlimento;
    /**
     * quantidadeAlimento * precoAlimento
     */
    BigDecimal valorTotal;
}
