package com.fiap.lanchonete.domain.mapper;

import com.fiap.lanchonete.domain.model.PedidoAlimento;
import com.fiap.lanchonete.domain.pojo.PedidoAlimentoDto;

public interface PedidoAlimentoMapper {
    PedidoAlimento toDomain(PedidoAlimentoDto dto);
}
