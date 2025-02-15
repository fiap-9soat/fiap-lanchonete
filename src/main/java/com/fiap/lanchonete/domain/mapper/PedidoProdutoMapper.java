package com.fiap.lanchonete.domain.mapper;

import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.model.PedidoProduto;
import com.fiap.lanchonete.domain.pojo.ListaPedidoProdutoDto;
import com.fiap.lanchonete.domain.pojo.ProdutoDto;
import com.fiap.lanchonete.domain.pojo.PedidoProdutoDto;

public interface PedidoProdutoMapper {

    PedidoProduto toDomain(PedidoProdutoDto dto);

    PedidoProduto toDomain(ProdutoDto createPedidoDto);

    ListaPedidoProdutoDto toDomain(PedidoProduto pedidoProduto);
}
