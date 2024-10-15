package com.fiap.lanchonete.domain.ports.in;

import com.fiap.lanchonete.domain.model.Cliente;

public interface ClienteService {
    public int cadastrarCliente(Cliente cliente);
    public Cliente consultarClientePorCpf(String cpf);
}
