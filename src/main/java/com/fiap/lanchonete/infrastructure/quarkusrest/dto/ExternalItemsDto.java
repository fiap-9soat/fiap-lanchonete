package com.fiap.lanchonete.infrastructure.quarkusrest.dto;

import java.math.BigDecimal;

public record ExternalItemsDto(
        String title,
        BigDecimal unit_price,
        Integer quantity,
        String unit_measure,
        BigDecimal total_amount) {

}
