package com.microservice.quarkus.repository;

import com.microservice.quarkus.dbo.ClienteEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepositoryPanache implements PanacheRepository<ClienteEntity> {
}
