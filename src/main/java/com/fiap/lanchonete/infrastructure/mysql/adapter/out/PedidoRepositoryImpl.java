package com.fiap.lanchonete.infrastructure.mysql.adapter.out;

import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.pojo.CreatePedidoDto;
import com.fiap.lanchonete.domain.ports.out.PedidoRepository;
import com.fiap.lanchonete.infrastructure.mysql.dao.PedidoPanacheRepository;

import java.util.List;

public class PedidoRepositoryImpl extends PedidoPanacheRepository implements PedidoRepository {

    PedidoPanacheRepository pedidoPanacheRepository;

    @Override
    public void criarPedido(Pedido pedido) {
    }

    @Override
    public void atualizarPedido(Pedido pedido) {

    }

    @Override
    public void removerPedido(Pedido pedido) {
    }

    @Override
    public Pedido buscarPedidoPorId(Integer id) {
        return null;
    }

    @Override
    public List<Pedido> buscarPedidosPorCodigoCliente(Integer codigoCliente) {
        return List.of();
    }

    @Override
    public Integer criarPedido(CreatePedidoDto createPedidoDto) {

        return null;
    }
}
