package com.fiap.lanchonete.application.mapper;

import com.fiap.lanchonete.domain.mapper.AlimentoMapper;
import com.fiap.lanchonete.domain.model.Alimento;
import com.fiap.lanchonete.domain.pojo.CreateAlimentoDto;
import com.fiap.lanchonete.domain.pojo.EditAlimentoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "jakarta", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface AlimentoDTOMapper extends AlimentoMapper {

    @Named("localDateTimeParaInstant")
    default Instant localDateTimeParaInstant(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.of("America/Sao_Paulo");
        return localDateTime.atZone(zone).toInstant();
    }

    @Override
    @Mapping(source = "codigoTipoAlimento", target = "codigoTipoAlimento")
    @Mapping(target = "codigoAlimento", ignore = true)
    @Mapping(source = "precoAlimento", target = "precoAlimento")
    @Mapping(target = "tsAlter", expression = "java(java.time.Instant.now())")
    Alimento toDomain(CreateAlimentoDto dto);

    @Override
    @Mapping(source = "codigoTipoAlimento", target = "codigoTipoAlimento")
    @Mapping(source = "codigoAlimento", target = "codigoAlimento")
    @Mapping(target = "tsAlter", expression = "java(java.time.Instant.now())")
    Alimento toDomain(EditAlimentoDto dto);

}
