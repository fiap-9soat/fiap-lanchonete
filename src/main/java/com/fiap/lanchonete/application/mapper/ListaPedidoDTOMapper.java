package com.fiap.lanchonete.application.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import com.fiap.lanchonete.domain.mapper.ListaPedidoMapper;
import com.fiap.lanchonete.domain.pojo.ListaPedidoDto;
import com.fiap.lanchonete.domain.pojo.ListaPedidoAlimentoDto;
import com.fiap.lanchonete.domain.pojo.ListaPedidosDto;
import com.fiap.lanchonete.domain.pojo.PedidoAlimentoListaDto;

@Mapper(componentModel = "jakarta")
public interface ListaPedidoDTOMapper extends ListaPedidoMapper {

    @Named("instantParaLocalDateTime")
    default LocalDateTime instantParaLocalDateTime(Instant instant) {
        ZoneId zone = ZoneId.of("America/Sao_Paulo");
        return LocalDateTime.ofInstant(instant, zone);
    }

    @Override
    ListaPedidosDto fromDomain(ListaPedidoDto dto);

    @Override
    ListaPedidoAlimentoDto toDomain(PedidoAlimentoListaDto dto);

}
