package com.fiap.lanchonete.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QrCodeDto {
    Integer codigoPedido;
    String qrCode;
}