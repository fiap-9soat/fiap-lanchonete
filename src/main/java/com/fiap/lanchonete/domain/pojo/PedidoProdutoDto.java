package com.fiap.lanchonete.domain.pojo;

public record PedidoProdutoDto(
        Integer codigoPedido,
        Short codigoTipoProduto,
        Short codigoProduto,
        Short quantidadeProduto) {
}
