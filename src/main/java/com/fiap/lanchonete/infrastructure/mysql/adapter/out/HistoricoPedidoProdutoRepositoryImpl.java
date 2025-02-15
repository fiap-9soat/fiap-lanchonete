package com.fiap.lanchonete.infrastructure.mysql.adapter.out;

import com.fiap.lanchonete.domain.model.HistoricoPedidoProduto;
import com.fiap.lanchonete.domain.ports.out.HistoricoPedidoProdutoRepository;
import com.fiap.lanchonete.infrastructure.mysql.dao.HistoricoPedidoProdutoPanacheRepository;
import com.fiap.lanchonete.infrastructure.mysql.entity.HistoricoPedidoProdutoEntity;
import com.fiap.lanchonete.infrastructure.mysql.mapper.HistoricoPedidoProdutoEntityMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HistoricoPedidoProdutoRepositoryImpl implements HistoricoPedidoProdutoRepository {

    HistoricoPedidoProdutoPanacheRepository historicoPedidoProdutoPanacheRepository;

    HistoricoPedidoProdutoEntityMapper historicoPedidoProdutoEntityMapper;

    @Override
    public void insert(HistoricoPedidoProduto historicoPedido) {
        HistoricoPedidoProdutoEntity historicoPedidoProdutoEntity = historicoPedidoProdutoEntityMapper
                .toEntity(historicoPedido);
        historicoPedidoProdutoPanacheRepository.persist(historicoPedidoProdutoEntity);
    }

}
