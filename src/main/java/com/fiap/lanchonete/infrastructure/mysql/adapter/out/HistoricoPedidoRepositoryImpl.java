package com.fiap.lanchonete.infrastructure.mysql.adapter.out;

import com.fiap.lanchonete.domain.model.HistoricoPedido;
import com.fiap.lanchonete.domain.ports.out.HistoricoPedidoRepository;
import com.fiap.lanchonete.infrastructure.mysql.dao.HistoricoPedidoPanacheRepository;
import com.fiap.lanchonete.infrastructure.mysql.entity.HistoricoPedidoEntity;
import com.fiap.lanchonete.infrastructure.mysql.mapper.HistoricoPedidoEntityMapper;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class HistoricoPedidoRepositoryImpl implements HistoricoPedidoRepository {

    HistoricoPedidoPanacheRepository historicoPedidoPanacheRepository;

    HistoricoPedidoEntityMapper historicoPedidoEntityMapper;

    @Override
    public void insert(HistoricoPedido historicoPedido) {
        HistoricoPedidoEntity historicoPedidoEntity = historicoPedidoEntityMapper.toEntity(historicoPedido);
        historicoPedidoPanacheRepository.persist(historicoPedidoEntity);
    }

    @Override
    public List<HistoricoPedido> findByCodigoPedido(Integer codigoPedido) {
        return historicoPedidoPanacheRepository.find("codigoPedido", codigoPedido)
                .stream()
                .map(historicoPedidoEntityMapper::toDomain)
                .toList();
    }
}
