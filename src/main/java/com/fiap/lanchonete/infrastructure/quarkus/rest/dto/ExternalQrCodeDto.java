package com.fiap.lanchonete.infrastructure.quarkus.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExternalQrCodeDto {
    private String qr_data;
    private String in_store_order_id;
}
