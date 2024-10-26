package com.fiap.lanchonete.infrastructure.mysql.adapter.out;

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
        PedidoEntity entidade = pedidoEntityMapper.toEntity(pedido);
        List<PedidoEntity> resposta = pedidoPanacheRepository.getEntityManager()
                .createQuery("""
                            SELECT pe
                            FROM PedidoEntity pe
                            WHERE codigoCliente = :codigoCliente
                        """, PedidoEntity.class)
                .setParameter("codigoCliente", entidade.getCodigoCliente())
                .getResultList();
        ;
        if (resposta.isEmpty()) {
            pedidoPanacheRepository.persist(entidade);
            return entidade.getCodigoPedido();
        }

        return resposta.get(0).getCodigoPedido();
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

}
