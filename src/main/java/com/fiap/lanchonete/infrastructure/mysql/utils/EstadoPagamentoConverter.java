package com.fiap.lanchonete.infrastructure.mysql.utils;

import com.fiap.lanchonete.domain.enums.EstadoPagamento;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EstadoPagamentoConverter implements AttributeConverter<EstadoPagamento, Character> {
    @Override
    public Character convertToDatabaseColumn(EstadoPagamento attribute) {
        if (attribute == null)
            return null;

        if (attribute.equals(EstadoPagamento.APROVADO)) {
            return 'A';
        }

        return null;
    }

    @Override
    public EstadoPagamento convertToEntityAttribute(Character dbData) {
        if (dbData == 'A') {
            return EstadoPagamento.APROVADO;
        }
        return EstadoPagamento.RECUSADO;
    }

}
