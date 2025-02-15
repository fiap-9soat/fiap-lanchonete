package com.fiap.lanchonete.infrastructure.mysql.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.fiap.lanchonete.domain.pojo.ListaPedidoProdutoDto;
import com.fiap.lanchonete.infrastructure.mysql.entity.PedidoProdutoEntity;

@Mapper(componentModel = "jakarta")
public interface PedidoProdutoListaMapper {

    PedidoProdutoListaMapper INSTANCE = Mappers.getMapper(PedidoProdutoListaMapper.class);

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

    @Mapping(source = "codigoTipoProduto", target = "codigoTipoProduto")
    @Mapping(source = "codigoProduto", target = "codigoProduto")
    @Mapping(source = "quantidadeProduto", target = "quantidadeProduto")
    @Mapping(target = "codigoPedido", ignore = true)
    @Mapping(target = "produto", ignore = true)
    PedidoProdutoEntity toEntity(ListaPedidoProdutoDto domain);

    @Mapping(source = "codigoTipoProduto", target = "codigoTipoProduto")
    @Mapping(source = "codigoProduto", target = "codigoProduto")
    @Mapping(source = "quantidadeProduto", target = "quantidadeProduto")
    @Mapping(target = "valorProduto", ignore = true)
    @Mapping(target = "valorTotal", ignore = true)
    ListaPedidoProdutoDto toDomain(PedidoProdutoEntity domain);
}
