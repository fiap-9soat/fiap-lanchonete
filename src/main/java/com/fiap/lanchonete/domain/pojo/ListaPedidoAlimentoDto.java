package com.fiap.lanchonete.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
public class ListaPedidoAlimentoDto {
    Short codigoTipoAlimento;
    Short codigoAlimento;
    Short quantidadeAlimento;
    BigDecimal valorAlimento;
    /**
     * quantidadeAlimento * precoAlimento
     */
    BigDecimal valorTotal;
}
