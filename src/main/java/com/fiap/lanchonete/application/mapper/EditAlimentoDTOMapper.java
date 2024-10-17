package com.fiap.lanchonete.application.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;

import com.fiap.lanchonete.application.dto.EditAlimentoDto;
import com.fiap.lanchonete.domain.model.Alimento;

@Mapper(componentModel = "jakarta", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface EditAlimentoDTOMapper {

    @Named("localDateTimeParaInstant")
    default Instant localDateTimeParaInstant(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.of("America/Sao_Paulo");
        return localDateTime.atZone(zone).toInstant();
    }

    @Mapping(source = "codigoTipoAlimento", target = "codigoTipoAlimento")
    @Mapping(source = "codigoAlimento", target = "codigoAlimento")
    @Mapping(target = "tsAlter", expression = "java(java.time.Instant.now())")
    public Alimento toDomain(EditAlimentoDto dto);
}
