package com.fiap.lanchonete.infrastructure.mysql.dao;

import com.fiap.lanchonete.infrastructure.mysql.entity.PedidoProdutoEntity;
import com.fiap.lanchonete.infrastructure.mysql.entity.PedidoProdutoEntityId;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoProdutoPanacheRepository
        implements PanacheRepositoryBase<PedidoProdutoEntity, PedidoProdutoEntityId> {

}
