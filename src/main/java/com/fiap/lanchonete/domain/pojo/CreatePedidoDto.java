package com.fiap.lanchonete.domain.pojo;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePedidoDto {
    @Nullable
    private Integer codigoCliente;
    @Nullable
    private Integer codigoPedido;
    private Short codigoTipoAlimento;
    private Short codigoAlimento;
    private Integer quantidadeAlimento;
}
