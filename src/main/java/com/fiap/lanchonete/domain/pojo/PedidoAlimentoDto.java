package com.fiap.lanchonete.domain.pojo;

public record PedidoAlimentoDto(
        Integer codigoPedido,
        Short codigoTipoAlimento,
        Short codigoAlimento,
        Short quantidadeAlimento) {
}
