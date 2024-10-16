package com.fiap.lanchonete.application.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.fiap.lanchonete.application.dto.CreateAlimentoDto;
import com.fiap.lanchonete.domain.model.Alimento;

@Mapper(componentModel = "jakarta")
public interface AlimentoDTOMapper {

    @Named("localDateTimeParaInstant")
    default Instant localDateTimeParaInstant(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.of("America/Sao_Paulo");
        return localDateTime.atZone(zone).toInstant();
    }

    @Mapping(source = "codigoTipoAlimento", target = "codigoTipoAlimento")
    @Mapping(source = "codigoAlimento", target = "codigoAlimento")
    @Mapping(source = "tsAlter", target = "tsAlter", qualifiedByName = "localDateTimeParaInstant")
    public Alimento toDomain(CreateAlimentoDto dto);
}
