package com.fiap.lanchonete.domain.pojo;

public record CreatePedidoDto(
    Integer codigoCliente,
    Short codigoTipoAlimento,
    Short codigoAlimento,
    Integer quantidadeAlimento) {
}
