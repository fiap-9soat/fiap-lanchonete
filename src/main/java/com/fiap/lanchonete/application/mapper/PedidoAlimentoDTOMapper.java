package com.fiap.lanchonete.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fiap.lanchonete.domain.mapper.PedidoAlimentoMapper;
import com.fiap.lanchonete.domain.model.PedidoAlimento;
import com.fiap.lanchonete.domain.pojo.PedidoAlimentoDto;

@Mapper(componentModel = "jakarta")
public interface PedidoAlimentoDTOMapper extends PedidoAlimentoMapper {

    @Override
    @Mapping(source = "codigoPedido", target = "codigoPedido")
    @Mapping(source = "codigoTipoAlimento", target = "codigoTipoAlimento")
    @Mapping(source = "codigoAlimento", target = "codigoAlimento")
    @Mapping(source = "quantidadeAlimento", target = "quantidadeAlimento")
    PedidoAlimento toDomain(PedidoAlimentoDto dto);
}
