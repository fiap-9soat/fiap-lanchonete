package com.fiap.lanchonete.domain.ports.out;

import com.fiap.lanchonete.domain.model.Cliente;

import java.util.List;

public interface ClienteRepository {
    public int insertCliente(Cliente cliente);
    public Cliente getClienteById(int id);
    public List<Cliente> getAllClientes();
    public Cliente getClienteByCpf(String cpf);
}
