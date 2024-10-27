package com.fiap.lanchonete.application.mapper;

import com.fiap.lanchonete.domain.mapper.PedidoAlimentoMapper;
import com.fiap.lanchonete.domain.model.PedidoAlimento;
import com.fiap.lanchonete.domain.pojo.CreatePedidoDto;
import com.fiap.lanchonete.domain.pojo.PedidoAlimentoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jakarta")
public interface PedidoAlimentoDTOMapper extends PedidoAlimentoMapper {

    @Override
    @Mapping(source = "codigoPedido", target = "codigoPedido")
    @Mapping(source = "codigoTipoAlimento", target = "codigoTipoAlimento")
    @Mapping(source = "codigoAlimento", target = "codigoAlimento")
    @Mapping(source = "quantidadeAlimento", target = "quantidadeAlimento")
    PedidoAlimento toDomain(PedidoAlimentoDto dto);

    @Override
    @Mapping(source = "codigoPedido", target = "codigoPedido")
    @Mapping(source = "codigoAlimento", target = "codigoAlimento")
    @Mapping(source = "codigoTipoAlimento", target = "codigoTipoAlimento")
    @Mapping(source = "quantidadeAlimento", target = "quantidadeAlimento")
    PedidoAlimento toDomain(CreatePedidoDto createPedidoDto);
}
