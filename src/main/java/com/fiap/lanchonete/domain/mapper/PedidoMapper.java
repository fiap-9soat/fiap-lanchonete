package com.fiap.lanchonete.domain.mapper;

import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.pojo.ClienteDto;
import com.fiap.lanchonete.domain.pojo.CreatePedidoDto;

public interface PedidoMapper {
    Pedido toDomain(ClienteDto pedidoDto, CreatePedidoDto createAlimentoDto);
}
