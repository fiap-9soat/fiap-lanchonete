package com.fiap.lanchonete.infrastructure.mysql.adapter.out;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.pojo.ListaPedidoDto;
import com.fiap.lanchonete.domain.ports.out.PedidoRepository;
import com.fiap.lanchonete.infrastructure.mysql.dao.PedidoPanacheRepository;
import com.fiap.lanchonete.infrastructure.mysql.entity.PedidoEntity;
import com.fiap.lanchonete.infrastructure.mysql.mapper.ListaPedidoEntityMapper;
import com.fiap.lanchonete.infrastructure.mysql.mapper.PedidoProdutoListaMapper;
import com.fiap.lanchonete.infrastructure.mysql.mapper.PedidoEntityMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PedidoRepositoryImpl implements PedidoRepository {

    PedidoPanacheRepository pedidoPanacheRepository;

    PedidoEntityMapper pedidoEntityMapper;

    ListaPedidoEntityMapper listaPedidoEntityMapper;

    PedidoProdutoListaMapper pedidoProdutoListaMapper;

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
    public List<ListaPedidoDto> listarPedidos() {
        List<PedidoEntity> listaPedidosEntity = pedidoPanacheRepository.list("""
                SELECT pe
                FROM PedidoEntity pe
                WHERE estadoPedido NOT IN (EstadoPedido.CANCELADO,
                    EstadoPedido.INICIADO, EstadoPedido.FINALIZADO)
                """);
        return listaPedidosEntity.stream().map(entity -> {
            ListaPedidoDto pedido = listaPedidoEntityMapper.toDomain(entity);
            pedido.setListaPedidoProdutos(entity.getPedidoProduto().stream().map(produto -> {
                return pedidoProdutoListaMapper.toDomain(produto);
            }).toList());
            return pedido;
        }).toList();
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
    public Pedido buscarPedidoPorIdExterno(Integer idExterno) {
        var pedido = pedidoPanacheRepository.find("""
                codigoIdExterno = ?1
                """, idExterno).singleResult();
        return pedidoEntityMapper.toDomain(pedido);
    }

    @Override
    public List<ListaPedidoDto> buscarPedidosPorCodigoCliente(Integer codigoCliente) {
        List<PedidoEntity> listaPedidosEntity = pedidoPanacheRepository.list("""
                SELECT pe
                FROM PedidoEntity pe
                WHERE codigoCliente = ?1
                AND estadoPedido NOT IN (EstadoPedido.CANCELADO,
                    EstadoPedido.INICIADO, EstadoPedido.FINALIZADO)
                """, codigoCliente);
        return listaPedidosEntity.stream().map(entity -> {
            ListaPedidoDto pedido = listaPedidoEntityMapper.toDomain(entity);
            pedido.setListaPedidoProdutos(entity.getPedidoProduto().stream().map(produto -> {
                return pedidoProdutoListaMapper.toDomain(produto);
            }).toList());
            return pedido;
        }).toList();
    }

    @Override
    public List<ListaPedidoDto> buscarPedidosPorCodigoPedido(Integer codigoPedido) {
        List<PedidoEntity> listaPedidosEntity = pedidoPanacheRepository.list("""
                SELECT pe
                FROM PedidoEntity pe
                WHERE codigoPedido = ?1
                AND estadoPedido = EstadoPedido.INICIADO
                """, codigoPedido);
        return listaPedidosEntity.stream().map(entity -> {
            ListaPedidoDto pedido = listaPedidoEntityMapper.toDomain(entity);
            pedido.setListaPedidoProdutos(entity.getPedidoProduto().stream().map(produto -> {
                var pedidoProduto = pedidoProdutoListaMapper.toDomain(produto);
                pedidoProduto.setValorProduto(produto.getProduto().getPrecoProduto());
                pedidoProduto.setValorTotal(
                        produto.getProduto().getPrecoProduto()
                                .multiply(new BigDecimal(produto.getQuantidadeProduto().intValue())));
                return pedidoProduto;
            }).toList());
            return pedido;
        }).toList();
    }

    @Override
    public void registrarIdPedidoExterno(Integer id, String idPedidoExterno) {
        pedidoPanacheRepository.update("codigoIdExterno = ?1 WHERE codigoPedido = ?2", idPedidoExterno, id);
    }
}
