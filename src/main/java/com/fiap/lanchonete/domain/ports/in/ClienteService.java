package com.fiap.lanchonete.domain.ports.in;

import com.fiap.lanchonete.domain.model.Cliente;
import com.fiap.lanchonete.domain.pojo.CreateClienteDto;

import java.util.List;

public interface ClienteService {
    public int cadastrarCliente(CreateClienteDto createClienteDto);

    public Cliente consultarClientePorCpf(String cpf);

    public List<Cliente> listarClientes();
}
