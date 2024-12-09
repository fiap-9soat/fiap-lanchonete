package com.fiap.lanchonete.infrastructure.quarkusrest.dto;

public record ExternalItemsDto(
        String title,
        String category,
        Double unit_price,
        Integer quantity,
        String unit_measure,
        Double total_amount) {

}
