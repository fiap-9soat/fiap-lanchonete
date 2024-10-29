package com.fiap.lanchonete.infrastructure.mysql.adapter.out;

import java.util.ArrayList;
import java.util.List;

import com.fiap.lanchonete.domain.model.EstadoPedido;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.model.PedidoAlimento;
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
    public void criarPedido(Integer codigoPedido, Pedido pedido) {
        PedidoEntity entity = pedidoEntityMapper.toEntity(pedido);
        entity.setCodigoPedido(codigoPedido);
        pedidoPanacheRepository.persist(entity);
    }

    @Override
    public List<Pedido> checaSeClienteJaTemPedido(Pedido pedido) {
        List<Pedido> listaResposta = new ArrayList<>();
        pedidoPanacheRepository.find("""
                SELECT pe
                FROM PedidoEntity pe
                WHERE codigoCliente = ?1
                AND estadoPedido = EstadoPedido.INICIADO
                """,
                pedido.getCodigoCliente()).stream()
                .forEach(entidade -> listaResposta.add(pedidoEntityMapper.toDomain(entidade)));
        return listaResposta;
    }

    @Override
    public List<Pedido> checaPedidoDeCLienteAnonimo(Pedido pedido) {
        List<Pedido> listaResposta = new ArrayList<>();
        pedidoPanacheRepository.find("""
                SELECT pe
                FROM PedidoEntity pe
                WHERE codigoPedido = ?1
                AND estadoPedido = EstadoPedido.INICIADO
                """,
                pedido.getCodigoPedido()).stream()
                .forEach(entidade -> listaResposta.add(pedidoEntityMapper.toDomain(entidade)));
        return listaResposta;
    }

    @Override
    public Integer retornaMaiorCodigoPedido(Pedido pedido) {
        return pedidoPanacheRepository.getEntityManager()
                .createQuery(
                        """
                                SELECT IFNULL(MAX(pe.codigoPedido)+1, "1")
                                FROM PedidoEntity pe
                                    """,
                        Integer.class)
                .getSingleResult();
    }

    @Override
    public List<PedidoAlimento> listarPedidos() {
        List<PedidoEntity> listaPedidos = pedidoPanacheRepository.find("""
                SELECT pe
                FROM PedidoEntity pe
                """).list();
        listaPedidos.stream().forEach(a -> a.getPedidoAlimento());
        return List.of();
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPedidosPorCodigoCliente'");
    }

}
