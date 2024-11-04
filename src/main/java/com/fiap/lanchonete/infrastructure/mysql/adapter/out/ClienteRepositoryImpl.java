package com.fiap.lanchonete.infrastructure.mysql.adapter.out;

import com.fiap.lanchonete.domain.model.Cliente;
import com.fiap.lanchonete.domain.ports.out.ClienteRepository;
import com.fiap.lanchonete.infrastructure.mysql.dao.ClientePanacheRepository;
import com.fiap.lanchonete.infrastructure.mysql.entity.ClienteEntity;
import com.fiap.lanchonete.infrastructure.mysql.mapper.ClienteEntityMapper;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ClienteRepositoryImpl implements ClienteRepository {

    ClientePanacheRepository clientePanacheRepository;

    ClienteEntityMapper clienteEntityMapper;

    @Override
    public int insertCliente(Cliente cliente) {
        ClienteEntity entity = clienteEntityMapper.toEntity(cliente);
        clientePanacheRepository.persist(entity);
        return entity.getCodigoCliente();
    }

    @Override
    public Cliente getClienteById(int id) {
        ClienteEntity entity = clientePanacheRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);
        return clienteEntityMapper.toDomain(entity);
    }

    @Override
    public List<Cliente> getAllClientes() {
        return clientePanacheRepository.listAll().stream().map(clienteEntityMapper::toDomain).toList();
    }

    @Override
    public Cliente getClienteByCpf(String cpf) {
        ClienteEntity entity = clientePanacheRepository.find("cpf", cpf).firstResultOptional().orElseThrow(NotFoundException::new);
        return clienteEntityMapper.toDomain(entity);
    }
}
