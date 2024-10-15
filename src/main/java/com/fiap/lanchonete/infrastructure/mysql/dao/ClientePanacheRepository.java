package com.fiap.lanchonete.infrastructure.mysql.dao;

import com.fiap.lanchonete.infrastructure.mysql.entity.ClienteEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientePanacheRepository implements PanacheRepositoryBase<ClienteEntity, Integer> {
}
