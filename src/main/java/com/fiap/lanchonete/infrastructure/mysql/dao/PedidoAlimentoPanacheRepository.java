package com.fiap.lanchonete.infrastructure.mysql.dao;

import com.fiap.lanchonete.infrastructure.mysql.entity.PedidoAlimentoEntity;
import com.fiap.lanchonete.infrastructure.mysql.entity.PedidoAlimentoEntityId;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoAlimentoPanacheRepository
                implements PanacheRepositoryBase<PedidoAlimentoEntity, PedidoAlimentoEntityId> {

}
