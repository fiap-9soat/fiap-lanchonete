package com.fiap.lanchonete.infrastructure.mysql.dao;

import com.fiap.lanchonete.infrastructure.mysql.entity.AlimentoEntity;
import com.fiap.lanchonete.infrastructure.mysql.entity.AlimentoEntityId;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AlimentoPanacheRepository implements PanacheRepositoryBase<AlimentoEntity, AlimentoEntityId> {

}
