package com.fiap.lanchonete.domain.mapper;

import com.fiap.lanchonete.domain.pojo.ListaPedidoDto;
import com.fiap.lanchonete.domain.pojo.ListaPedidoAlimentoDto;
import com.fiap.lanchonete.domain.pojo.ListaPedidosDto;
import com.fiap.lanchonete.domain.pojo.PedidoAlimentoListaDto;

public interface ListaPedidoMapper {
    ListaPedidosDto fromDomain(ListaPedidoDto dto);

    ListaPedidoAlimentoDto toDomain(PedidoAlimentoListaDto dto);
}
