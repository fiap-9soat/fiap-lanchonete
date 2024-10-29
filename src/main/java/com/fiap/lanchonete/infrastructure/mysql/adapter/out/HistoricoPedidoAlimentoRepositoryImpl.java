package com.fiap.lanchonete.infrastructure.mysql.adapter.out;

import com.fiap.lanchonete.domain.model.HistoricoPedidoAlimento;
import com.fiap.lanchonete.domain.ports.out.HistoricoPedidoAlimentoRepository;
import com.fiap.lanchonete.infrastructure.mysql.dao.HistoricoPedidoAlimentoPanacheRepository;
import com.fiap.lanchonete.infrastructure.mysql.entity.HistoricoPedidoAlimentoEntity;
import com.fiap.lanchonete.infrastructure.mysql.mapper.HistoricoPedidoAlimentoEntityMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HistoricoPedidoAlimentoRepositoryImpl implements HistoricoPedidoAlimentoRepository {

    HistoricoPedidoAlimentoPanacheRepository historicoPedidoAlimentoPanacheRepository;

    HistoricoPedidoAlimentoEntityMapper historicoPedidoAlimentoEntityMapper;

    @Override
    public void insert(HistoricoPedidoAlimento historicoPedido) {
        HistoricoPedidoAlimentoEntity historicoPedidoAlimentoEntity = historicoPedidoAlimentoEntityMapper
                .toEntity(historicoPedido);
        historicoPedidoAlimentoPanacheRepository.persist(historicoPedidoAlimentoEntity);
    }

}
