package com.fiap.lanchonete.infrastructure.mysql.adapter.out;

import com.fiap.lanchonete.domain.model.PedidoAlimento;
import com.fiap.lanchonete.domain.ports.out.PedidoAlimentoRepository;
import com.fiap.lanchonete.infrastructure.mysql.dao.PedidoAlimentoPanacheRepository;
import com.fiap.lanchonete.infrastructure.mysql.entity.PedidoAlimentoEntity;
import com.fiap.lanchonete.infrastructure.mysql.mapper.PedidoAlimentoEntityMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PedidoAlimentoRepositoryImpl implements PedidoAlimentoRepository {

    PedidoAlimentoPanacheRepository pedidoAlimentoPanacheRepository;

    PedidoAlimentoEntityMapper pedidoAlimentoEntityMapper;

    @Override
    public void inserirAlimentoPedido(Integer codigoPedido, PedidoAlimento pedidoAlimento) {
        PedidoAlimentoEntity entity = pedidoAlimentoEntityMapper.toEntity(pedidoAlimento);
        pedidoAlimentoPanacheRepository.persist(entity);
    }

}
