package com.fiap.lanchonete.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class PedidoQrCodeDto {
    private Integer codigoPedido;
    private String qrCode;
}
