package com.fiap.lanchonete.infrastructure.mysql.dao;

import com.fiap.lanchonete.infrastructure.mysql.entity.HistoricoPedidoAlimentoEntity;
import com.fiap.lanchonete.infrastructure.mysql.entity.HistoricoPedidoAlimentoEntityId;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HistoricoPedidoAlimentoPanacheRepository
                implements PanacheRepositoryBase<HistoricoPedidoAlimentoEntity, HistoricoPedidoAlimentoEntityId> {

}
