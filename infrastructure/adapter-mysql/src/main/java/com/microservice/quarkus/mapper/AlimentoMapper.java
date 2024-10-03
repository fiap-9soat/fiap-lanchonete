package com.microservice.quarkus.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.microservice.quarkus.dbo.AlimentoEntity;
import com.microservice.quarkus.domain.model.Alimento;


@Mapper(componentModel = "cdi")
public interface AlimentoMapper {

    AlimentoMapper INSTANCE = Mappers.getMapper(AlimentoMapper.class);

    @Named("instantParaLocalDateTime")
    default LocalDateTime instantParaLocalDateTime(Instant instant) {
        ZoneId zone = ZoneId.of("America/Sao_Paulo");
        return LocalDateTime.ofInstant(instant, zone);
    }
 
    @Mapping(source = "codigoTipoAlimento", target = "codigoTipoAlimento")
    @Mapping(source = "codigoAlimento", target = "codigoAlimento")
    @Mapping(source = "tsAlter", target = "tsAlter", qualifiedByName = "instantParaLocalDateTime")
    AlimentoEntity toDbo(Alimento domain);


}
