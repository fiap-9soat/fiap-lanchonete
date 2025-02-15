package com.fiap.lanchonete.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fiap.lanchonete.domain.mapper.PedidoProdutoMapper;
import com.fiap.lanchonete.domain.model.PedidoProduto;
import com.fiap.lanchonete.domain.pojo.ListaPedidoProdutoDto;
import com.fiap.lanchonete.domain.pojo.ProdutoDto;
import com.fiap.lanchonete.domain.pojo.PedidoProdutoDto;

@Mapper(componentModel = "jakarta")
public interface PedidoProdutoDTOMapper extends PedidoProdutoMapper {

    @Override
    @Mapping(source = "codigoPedido", target = "codigoPedido")
    @Mapping(source = "codigoTipoProduto", target = "codigoTipoProduto")
    @Mapping(source = "codigoProduto", target = "codigoProduto")
    @Mapping(source = "quantidadeProduto", target = "quantidadeProduto")
    PedidoProduto toDomain(PedidoProdutoDto dto);

    @Override
    @Mapping(source = "codigoProduto", target = "codigoProduto")
    @Mapping(source = "codigoTipoProduto", target = "codigoTipoProduto")
    @Mapping(source = "quantidadeProduto", target = "quantidadeProduto")
    @Mapping(target = "codigoPedido", ignore = true)
    PedidoProduto toDomain(ProdutoDto createPedidoDto);

    @Override
    @Mapping(target = "valorProduto", ignore = true)
    @Mapping(target = "valorTotal", ignore = true)
    ListaPedidoProdutoDto toDomain(PedidoProduto pedidoProduto);
}
