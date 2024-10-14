package com.microservice.quarkus.repository;

import com.microservice.quarkus.dbo.ClienteEntity;
import com.microservice.quarkus.domain.model.Cliente;
import com.microservice.quarkus.domain.ports.out.ClienteRepository;
import com.microservice.quarkus.mapper.ClienteMapper;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationScoped
public class ClienteRepositoryImpl implements ClienteRepository {

    ClienteRepositoryPanache clienteRepositoryPanache;

    ClienteMapper clienteMapper;

    public Cliente consultaCliente(String cpf) {
        ClienteEntity cliente = clienteRepositoryPanache.find("cpf", cpf).firstResult();
        return clienteMapper.toDomain(cliente);
    }

    public Long cadastrarCliente(String cpf, String nome, String sobrenome, String email) {
        Cliente cliente = Cliente.builder().cpf(cpf).nome(nome).sobrenome(sobrenome).email(email).build();
        ClienteEntity clienteEntity = clienteMapper.toDbo(cliente);
        clienteRepositoryPanache.persist(clienteEntity);

        return clienteEntity.getCodigoCliente();
    }
}
