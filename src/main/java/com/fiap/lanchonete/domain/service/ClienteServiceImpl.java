package com.fiap.lanchonete.domain.service;

import com.fiap.lanchonete.domain.mapper.ClienteMapper;
import com.fiap.lanchonete.domain.model.Cliente;
import com.fiap.lanchonete.domain.pojo.CreateClienteDto;
import com.fiap.lanchonete.domain.ports.in.ClienteService;
import com.fiap.lanchonete.domain.ports.out.ClienteRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;

    private ClienteMapper clienteMapper;

    @Override
    public int cadastrarCliente(CreateClienteDto createClienteDto) {
        Cliente cliente = clienteMapper.toDomain(createClienteDto);
        return clienteRepository.insertCliente(cliente);
    }

    @Override
    public Cliente consultarClientePorCpf(String cpf) {
        return clienteRepository.getClienteByCpf(cpf);
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.getAllClientes();
    }
}
