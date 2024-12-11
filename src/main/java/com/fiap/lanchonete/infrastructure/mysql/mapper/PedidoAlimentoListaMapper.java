package com.fiap.lanchonete.infrastructure.mysql.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.fiap.lanchonete.domain.pojo.ListaPedidoAlimentoDto;
import com.fiap.lanchonete.infrastructure.mysql.entity.PedidoAlimentoEntity;

@Mapper(componentModel = "jakarta")
public interface PedidoAlimentoListaMapper {

    PedidoAlimentoListaMapper INSTANCE = Mappers.getMapper(PedidoAlimentoListaMapper.class);

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
    @Mapping(source = "quantidadeAlimento", target = "quantidadeAlimento")
    @Mapping(target = "codigoPedido", ignore = true)
    PedidoAlimentoEntity toEntity(ListaPedidoAlimentoDto domain);

    @Mapping(source = "codigoTipoAlimento", target = "codigoTipoAlimento")
    @Mapping(source = "codigoAlimento", target = "codigoAlimento")
    @Mapping(source = "quantidadeAlimento", target = "quantidadeAlimento")
    ListaPedidoAlimentoDto toDomain(PedidoAlimentoEntity domain);
}
