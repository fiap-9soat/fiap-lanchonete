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
public class ListaPedidoProdutoDto {
    Short codigoTipoProduto;
    Short codigoProduto;
    Short quantidadeProduto;
    BigDecimal valorProduto;
    /**
     * quantidadeProduto * precoProduto
     */
    BigDecimal valorTotal;
}
