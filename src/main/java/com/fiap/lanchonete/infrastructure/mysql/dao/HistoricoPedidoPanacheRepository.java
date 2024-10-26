package com.fiap.lanchonete.infrastructure.mysql.dao;

import com.fiap.lanchonete.infrastructure.mysql.entity.HistoricoPedidoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HistoricoPedidoPanacheRepository implements PanacheRepositoryBase<HistoricoPedidoEntity, HistoricoPedidoEntity> {
}
