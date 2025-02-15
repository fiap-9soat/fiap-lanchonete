package com.fiap.lanchonete.infrastructure.mysql.dao;

import com.fiap.lanchonete.infrastructure.mysql.entity.HistoricoPedidoProdutoEntity;
import com.fiap.lanchonete.infrastructure.mysql.entity.HistoricoPedidoProdutoEntityId;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HistoricoPedidoProdutoPanacheRepository
        implements PanacheRepositoryBase<HistoricoPedidoProdutoEntity, HistoricoPedidoProdutoEntityId> {

}
