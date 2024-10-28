package com.fiap.lanchonete.infrastructure.mysql.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.fiap.lanchonete.domain.model.PedidoAlimento;
import com.fiap.lanchonete.infrastructure.mysql.entity.PedidoAlimentoEntity;

@Mapper(componentModel = "jakarta")
public interface PedidoAlimentoEntityMapper {

    PedidoAlimentoEntityMapper INSTANCE = Mappers.getMapper(PedidoAlimentoEntityMapper.class);

    @Mapping(source = "codigoPedido", target = "codigoPedido")
    @Mapping(source = "codigoTipoAlimento", target = "codigoTipoAlimento")
    @Mapping(source = "codigoAlimento", target = "codigoAlimento")
    @Mapping(source = "quantidadeAlimento", target = "quantidadeAlimento")
    PedidoAlimentoEntity toEntity(PedidoAlimento domain);

    @Mapping(source = "codigoPedido", target = "codigoPedido")
    @Mapping(source = "codigoTipoAlimento", target = "codigoTipoAlimento")
    @Mapping(source = "codigoAlimento", target = "codigoAlimento")
    @Mapping(source = "quantidadeAlimento", target = "quantidadeAlimento")
    PedidoAlimento toDomain(PedidoAlimentoEntity domain);

}
