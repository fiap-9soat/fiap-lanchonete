package com.fiap.lanchonete.infrastructure.quarkus.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public record ExternalInfoPedidoDto(
        String external_reference,
        String title,
        String description,
        String notification_url,
        BigDecimal total_amount,
        List<ExternalItemsDto> items) {
}
