package com.fiap.lanchonete.infrastructure.mysql.adapter.out;

import java.time.LocalDateTime;
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
                """);
        List<ListaPedido> resposta = listaPedidosEntity.stream().map(entity -> {
            return new ListaPedido(
                    entity.getCodigoPedido(),
                    entity.getTsUltimoPedido().atZone(zone).toInstant(),
                    entity.getPedidoAlimento().stream().map(alimento -> new PedidoAlimentoLista(
                            alimento.getCodigoTipoAlimento(),
                            alimento.getCodigoAlimento(),
                            alimento.getQuantidadeAlimento())).toList());

        }).toList();

        return resposta;
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
    public List<ListaPedido> buscarPedidosPorCodigoCliente(Integer codigoCliente) {
        ZoneId zone = ZoneId.of("America/Sao_Paulo");
        List<PedidoEntity> listaPedidosEntity = pedidoPanacheRepository.list("""
                SELECT pe
                FROM PedidoEntity pe
                WHERE codigoCliente = ?1
                """, codigoCliente);
        List<ListaPedido> resposta = listaPedidosEntity.stream().map(entity -> {
            return new ListaPedido(
                    entity.getCodigoPedido(),
                    entity.getTsUltimoPedido().atZone(zone).toInstant(),
                    entity.getPedidoAlimento().stream().map(alimento -> new PedidoAlimentoLista(
                            alimento.getCodigoTipoAlimento(),
                            alimento.getCodigoAlimento(),
                            alimento.getQuantidadeAlimento())).toList());

        }).toList();
        return resposta;
    }

    @Override
    public void fazerCheckoutPedido(Pedido pedido) {
        pedidoPanacheRepository.update("""
                UPDATE PedidoEntity
                SET estadoPedido = EstadoPedido.RECEBIDO,
                tsUltimoPedido = ?1
                WHERE codigoPedido = ?2
                """,
                LocalDateTime.now(),
                pedido.getCodigoPedido());
    }

}
