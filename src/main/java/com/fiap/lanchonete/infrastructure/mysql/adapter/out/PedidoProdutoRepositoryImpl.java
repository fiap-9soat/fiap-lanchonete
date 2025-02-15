package com.fiap.lanchonete.infrastructure.mysql.adapter.out;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.tool.schema.spi.SqlScriptException;

import com.fiap.lanchonete.domain.model.PedidoProduto;
import com.fiap.lanchonete.domain.ports.out.PedidoProdutoRepository;
import com.fiap.lanchonete.infrastructure.mysql.dao.PedidoProdutoPanacheRepository;
import com.fiap.lanchonete.infrastructure.mysql.entity.PedidoProdutoEntity;
import com.fiap.lanchonete.infrastructure.mysql.entity.PedidoProdutoEntityId;
import com.fiap.lanchonete.infrastructure.mysql.mapper.PedidoProdutoEntityMapper;

import io.quarkus.panache.common.Parameters;
import jakarta.ws.rs.BadRequestException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PedidoProdutoRepositoryImpl implements PedidoProdutoRepository {

    PedidoProdutoPanacheRepository pedidoProdutoPanacheRepository;

    PedidoProdutoEntityMapper pedidoProdutoEntityMapper;

    @Override
    public void inserir(PedidoProduto pedidoProduto) {
        PedidoProdutoEntity entity = pedidoProdutoEntityMapper.toEntity(pedidoProduto);
        pedidoProdutoPanacheRepository.persist(entity);
    }

    @Override
    public void checarSeTipoProdutoJaExiste(PedidoProduto pedidoProduto) throws BadRequestException {
        PedidoProdutoEntity query = pedidoProdutoPanacheRepository.findById(new PedidoProdutoEntityId(
                pedidoProduto.getCodigoPedido(),
                pedidoProduto.getCodigoTipoProduto(),
                pedidoProduto.getCodigoProduto()));

        if (query != null) {
            throw new BadRequestException(
                    "Esse tipo de produto já foi incluido no sistema. Remova ou edite seu pedido.");
        }
    }

    @Override
    public void remover(PedidoProduto pedidoProduto) {
        pedidoProdutoPanacheRepository.delete("""
                FROM PedidoProdutoEntity pae
                WHERE codigoPedido = ?1
                AND codigoTipoProduto = ?2
                AND codigoProduto = ?3
                """,
                pedidoProduto.getCodigoPedido(),
                pedidoProduto.getCodigoTipoProduto(),
                pedidoProduto.getCodigoProduto());
    }

    @Override
    public void editar(PedidoProduto pedidoProduto) {
        pedidoProdutoPanacheRepository.update("""
                UPDATE PedidoProdutoEntity
                SET quantidadeProduto = ?1
                WHERE codigoPedido = ?2
                AND codigoTipoProduto = ?3
                AND codigoProduto = ?4
                """,
                pedidoProduto.getQuantidadeProduto(),
                pedidoProduto.getCodigoPedido(),
                pedidoProduto.getCodigoTipoProduto(),
                pedidoProduto.getCodigoProduto());
    }

    @Override
    public List<PedidoProduto> listar() {
        List<PedidoProduto> listaPedidoProduto = new ArrayList<>();
        List<PedidoProdutoEntity> listaPedidoProdutoEntity = pedidoProdutoPanacheRepository.find("""
                SELECT pa
                FROM PedidoProdutoEntity pa
                """).list();
        listaPedidoProdutoEntity.stream()
                .forEach(entity -> listaPedidoProduto.add(pedidoProdutoEntityMapper.toDomain(entity)));
        return listaPedidoProduto;
    }

    @Override
    public List<PedidoProduto> listarPorCodigoPedido(Integer codigoPedido) {
        return pedidoProdutoPanacheRepository
                .find("codigoPedido", codigoPedido)
                .stream()
                .map(pedidoProdutoEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void removerPorCodigoPedido(Integer codigoPedido) {
        pedidoProdutoPanacheRepository.delete("""
                FROM PedidoProdutoEntity pae
                WHERE codigoPedido = ?1
                """,
                codigoPedido);
    }

    @Override
    public void atualizarPedido(PedidoProduto pedidoProduto) {
        Parameters params = Parameters.with(
                "codigoTipoProduto", pedidoProduto.getCodigoTipoProduto())
                .and("codigoProduto", pedidoProduto.getCodigoProduto())
                .and("quantidadeProduto", pedidoProduto.getQuantidadeProduto())
                .and("codigoPedido", pedidoProduto.getCodigoPedido());
        pedidoProdutoPanacheRepository.update("""
                codigoTipoProduto = :codigoTipoProduto,
                codigoProduto = :codigoProduto,
                quantidadeProduto = :quantidadeProduto
                WHERE codigoPedido = :codigoPedido
                """, params);
    }
}
