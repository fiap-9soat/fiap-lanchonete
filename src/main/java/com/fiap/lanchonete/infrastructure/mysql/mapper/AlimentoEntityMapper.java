package com.fiap.lanchonete.infrastructure.mysql.mapper;

import com.fiap.lanchonete.domain.model.Alimento;
import com.fiap.lanchonete.infrastructure.mysql.entity.AlimentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "jakarta")
public interface AlimentoEntityMapper {

    AlimentoEntityMapper INSTANCE = Mappers.getMapper(AlimentoEntityMapper.class);

    @Named("instantParaLocalDateTime")
    default LocalDateTime instantParaLocalDateTime(Instant instant) {
        ZoneId zone = ZoneId.of("America/Sao_Paulo");
        return LocalDateTime.ofInstant(instant, zone);
    }

    @Named("localDateTimeParaInstant")
    default Instant localDateTimeParaInstant(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.of("America/Sao_Paulo");
        return localDateTime.atZone(zone).toInstant();
    }

    @Mapping(source = "codigoTipoAlimento", target = "codigoTipoAlimento")
    @Mapping(source = "codigoAlimento", target = "codigoAlimento")
    @Mapping(source = "tsAlter", target = "tsAlter", qualifiedByName = "localDateTimeParaInstant")
    Alimento toDomain(AlimentoEntity domain);

    @Mapping(source = "codigoTipoAlimento", target = "codigoTipoAlimento")
    @Mapping(source = "codigoAlimento", target = "codigoAlimento")
    @Mapping(source = "tsAlter", target = "tsAlter", qualifiedByName = "instantParaLocalDateTime")
    AlimentoEntity toEntity(Alimento domain);

}
