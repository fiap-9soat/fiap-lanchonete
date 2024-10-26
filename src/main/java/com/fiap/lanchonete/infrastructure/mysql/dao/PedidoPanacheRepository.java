package com.fiap.lanchonete.infrastructure.mysql.dao;

import com.fiap.lanchonete.infrastructure.mysql.entity.PedidoEntity;
import com.fiap.lanchonete.infrastructure.mysql.entity.PedidoEntityId;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoPanacheRepository implements PanacheRepositoryBase<PedidoEntity, PedidoEntityId> {

}
