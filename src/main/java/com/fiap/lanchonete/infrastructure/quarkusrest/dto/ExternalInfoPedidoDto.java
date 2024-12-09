package com.fiap.lanchonete.infrastructure.quarkusrest.dto;

import java.util.List;

public record ExternalInfoPedidoDto(
        String external_reference,
        String title,
        String description,
        String notification_url,
        Double total_amount,
        List<ExternalItemsDto> items) {
}
