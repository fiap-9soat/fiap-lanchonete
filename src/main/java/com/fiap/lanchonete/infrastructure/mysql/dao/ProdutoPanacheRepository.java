package com.fiap.lanchonete.infrastructure.mysql.dao;

import com.fiap.lanchonete.infrastructure.mysql.entity.ProdutoEntity;
import com.fiap.lanchonete.infrastructure.mysql.entity.ProdutoEntityId;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoPanacheRepository implements PanacheRepositoryBase<ProdutoEntity, ProdutoEntityId> {

}
