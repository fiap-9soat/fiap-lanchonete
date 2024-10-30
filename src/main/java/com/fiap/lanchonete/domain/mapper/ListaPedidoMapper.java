package com.fiap.lanchonete.domain.mapper;

import com.fiap.lanchonete.domain.model.ListaPedido;
import com.fiap.lanchonete.domain.model.PedidoAlimentoLista;
import com.fiap.lanchonete.domain.pojo.ListaPedidosDto;
import com.fiap.lanchonete.domain.pojo.PedidoAlimentoListaDto;

public interface ListaPedidoMapper {
    ListaPedidosDto fromDomain(ListaPedido dto);

    PedidoAlimentoLista toDomain(PedidoAlimentoListaDto dto);
}
