package com.fiap.lanchonete.domain.pojo;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutPedidoDto {
    @Nullable
    private Integer codigoCliente;
    private Integer codigoPedido;
}
