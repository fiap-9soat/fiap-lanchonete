package com.fiap.lanchonete.infrastructure.mysql.adapter.out;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.fiap.lanchonete.domain.model.ListaPedido;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.model.PedidoAlimentoLista;
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
    public List<ListaPedido> listarPedidos() {
        ZoneId zone = ZoneId.of("America/Sao_Paulo");
        List<PedidoEntity> listaPedidosEntity = pedidoPanacheRepository.list("""
                SELECT pe
                FROM PedidoEntity pe
                WHERE estadoPedido NOT IN (EstadoPedido.CANCELADO,
                    EstadoPedido.INICIADO, EstadoPedido.FINALIZADO)
                """);
        List<ListaPedido> resposta = listaPedidosEntity.stream()
                .map(entity -> {
                    return new ListaPedido(
                            entity.getCodigoPedido(),
                            entity.getEstadoPedido().getCodigo(),
                            entity.getEstadoPagamento() != null ? entity.getEstadoPagamento().getIndicadorPagamento()
                                    : null,
                            entity.getTsUltimoPedido().atZone(zone).toInstant(),
                            entity.getPedidoAlimento().stream().map(alimento -> new PedidoAlimentoLista(
                                    alimento.getCodigoTipoAlimento(),
                                    alimento.getCodigoAlimento(),
                                    alimento.getQuantidadeAlimento())).toList());

                }).toList();

        return resposta;
    }

    @Override
    public Pedido atualizarPedido(Pedido pedido) {
        PedidoEntity entity = pedidoEntityMapper.toEntity(pedido);
        pedidoPanacheRepository.getEntityManager().merge(entity);
        return pedidoEntityMapper.toDomain(entity);
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
    public List<ListaPedido> buscarPedidosPorCodigoCliente(Integer codigoCliente) {
        ZoneId zone = ZoneId.of("America/Sao_Paulo");
        List<PedidoEntity> listaPedidosEntity = pedidoPanacheRepository.list("""
                SELECT pe
                FROM PedidoEntity pe
                WHERE codigoCliente = ?1
                AND estadoPedido NOT IN (EstadoPedido.CANCELADO,
                    EstadoPedido.INICIADO, EstadoPedido.FINALIZADO)
                """, codigoCliente);
        List<ListaPedido> resposta = listaPedidosEntity.stream().map(entity -> {
            return new ListaPedido(
                    entity.getCodigoPedido(),
                    entity.getEstadoPedido().getCodigo(),
                    entity.getEstadoPagamento() != null ? entity.getEstadoPagamento().getIndicadorPagamento() : null,
                    entity.getTsUltimoPedido().atZone(zone).toInstant(),
                    entity.getPedidoAlimento().stream().map(alimento -> new PedidoAlimentoLista(
                            alimento.getCodigoTipoAlimento(),
                            alimento.getCodigoAlimento(),
                            alimento.getQuantidadeAlimento())).toList());

        }).toList();
        return resposta;
    }
}
