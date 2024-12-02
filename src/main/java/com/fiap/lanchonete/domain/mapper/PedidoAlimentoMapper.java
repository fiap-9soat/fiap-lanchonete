package com.fiap.lanchonete.domain.mapper;

import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.model.PedidoAlimento;
import com.fiap.lanchonete.domain.model.PedidoAlimentoLista;
import com.fiap.lanchonete.domain.pojo.AlimentoDto;
import com.fiap.lanchonete.domain.pojo.PedidoAlimentoDto;

public interface PedidoAlimentoMapper {

    PedidoAlimento toDomain(Pedido pedido);

    PedidoAlimento toDomain(PedidoAlimentoDto dto);

    PedidoAlimento toDomain(AlimentoDto createPedidoDto);

    PedidoAlimentoLista toDomain(PedidoAlimento pedidoAlimento);
}
