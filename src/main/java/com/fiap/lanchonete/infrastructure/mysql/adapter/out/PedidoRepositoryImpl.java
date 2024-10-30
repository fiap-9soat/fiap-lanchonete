package com.fiap.lanchonete.infrastructure.mysql.adapter.out;

import java.util.ArrayList;
import java.util.List;

import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.ports.out.PedidoRepository;
import com.fiap.lanchonete.infrastructure.mysql.dao.PedidoPanacheRepository;
import com.fiap.lanchonete.infrastructure.mysql.entity.PedidoEntity;
import com.fiap.lanchonete.infrastructure.mysql.mapper.PedidoEntityMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PedidoRepositoryImpl implements PedidoRepository {

    PedidoPanacheRepository pedidoPanacheRepository;

    PedidoEntityMapper pedidoEntityMapper;

    @Override
    public Integer criarPedido(Pedido pedido) {
        PedidoEntity entity = pedidoEntityMapper.toEntity(pedido);
        pedidoPanacheRepository.persist(entity);
        return entity.getCodigoPedido();
    }

    @Override
    public List<Pedido> checaSeClienteJaTemPedido(Pedido pedido) {
        List<Pedido> listaResposta = new ArrayList<>();
        pedidoPanacheRepository.find("""
                SELECT pe
                FROM PedidoEntity pe
                WHERE estadoPedido = EstadoPedido.INICIADO
                AND (?1 IS NOT NULL OR codigoPedido = ?2)
                AND (?2 IS NOT NULL OR codigoCliente = ?1)
                """,
                pedido.getCodigoCliente(),
                pedido.getCodigoPedido()).stream()
                .forEach(entidade -> listaResposta.add(pedidoEntityMapper.toDomain(entidade)));
        return listaResposta;
    }

    @Override
    public List<Pedido> listarPedidos() {
        List<Pedido> listaPedidos = new ArrayList<>();
        List<PedidoEntity> listaPedidosEntity = pedidoPanacheRepository.find("""
                SELECT pe
                FROM PedidoEntity pe
                """).list();
        listaPedidosEntity.stream().forEach(entity -> listaPedidos.add(pedidoEntityMapper.toDomain(entity)));

        return listaPedidos;
    }

    @Override
    public void atualizarPedido(Pedido pedido) {
        PedidoEntity entity = pedidoEntityMapper.toEntity(pedido);
        pedidoPanacheRepository.persist(entity);
    }

    @Override
    public void removerPedido(Integer codigoPedido) {
        pedidoPanacheRepository.deleteById(codigoPedido);
    }

    @Override
    public Pedido buscarPedidoPorId(Integer id) {
        return pedidoEntityMapper.toDomain(pedidoPanacheRepository.findById(id));
    }

    @Override
    public List<Pedido> buscarPedidosPorCodigoCliente(Integer codigoCliente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPedidosPorCodigoCliente'");
    }

}
