package com.fiap.lanchonete.infrastructure.mysql.adapter.out;

import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.ports.out.PedidoRepository;
import com.fiap.lanchonete.infrastructure.mysql.dao.PedidoPanacheRepository;
import com.fiap.lanchonete.infrastructure.mysql.entity.PedidoEntity;
import com.fiap.lanchonete.infrastructure.mysql.mapper.PedidoEntityMapper;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PedidoRepositoryImpl implements PedidoRepository {

    PedidoPanacheRepository pedidoPanacheRepository;

    PedidoEntityMapper pedidoEntityMapper;

    @Override
    public Integer criarPedido(Pedido pedido) {
        Integer proximoId = pedidoPanacheRepository.getEntityManager()
            .createQuery(
                """
                    SELECT IFNULL(MAX() + 1, "1")
                    FROM PedidoEntity pedido
                    WHERE pedido.codigoCliente = :codigoCliente
                    """,
                Integer.class
            )
            .setParameter("codigoCliente", pedido.getCodigoCliente())
            .getSingleResult();

        PedidoEntity entity = pedidoEntityMapper.toEntity(pedido);
        entity.setCodigoPedido(proximoId);

        pedidoPanacheRepository.persist(entity);

        return proximoId;
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
