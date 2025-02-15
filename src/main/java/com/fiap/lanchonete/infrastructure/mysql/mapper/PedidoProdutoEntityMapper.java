package com.fiap.lanchonete.infrastructure.mysql.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.fiap.lanchonete.domain.model.PedidoProduto;
import com.fiap.lanchonete.infrastructure.mysql.entity.PedidoProdutoEntity;

@Mapper(componentModel = "jakarta")
public interface PedidoProdutoEntityMapper {

    PedidoProdutoEntityMapper INSTANCE = Mappers.getMapper(PedidoProdutoEntityMapper.class);

    @Mapping(source = "codigoPedido", target = "codigoPedido")
    @Mapping(source = "codigoTipoProduto", target = "codigoTipoProduto")
    @Mapping(source = "codigoProduto", target = "codigoProduto")
    @Mapping(source = "quantidadeProduto", target = "quantidadeProduto")
    @Mapping(target = "produto", ignore = true)
    PedidoProdutoEntity toEntity(PedidoProduto domain);

    @Mapping(source = "codigoPedido", target = "codigoPedido")
    @Mapping(source = "codigoTipoProduto", target = "codigoTipoProduto")
    @Mapping(source = "codigoProduto", target = "codigoProduto")
    @Mapping(source = "quantidadeProduto", target = "quantidadeProduto")
    PedidoProduto toDomain(PedidoProdutoEntity domain);

}
