package com.fiap.lanchonete.infrastructure.mysql.adapter.out;

import com.fiap.lanchonete.domain.model.Produto;
import com.fiap.lanchonete.domain.ports.out.ProdutoRepository;
import com.fiap.lanchonete.infrastructure.mysql.dao.ProdutoPanacheRepository;
import com.fiap.lanchonete.infrastructure.mysql.entity.ProdutoEntity;
import com.fiap.lanchonete.infrastructure.mysql.entity.ProdutoEntityId;
import com.fiap.lanchonete.infrastructure.mysql.mapper.ProdutoEntityMapper;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ProdutoRepositoryImpl implements ProdutoRepository {

    ProdutoPanacheRepository produtoPanacheRepository;

    ProdutoEntityMapper produtoEntityMapper;

    @Override
    public void insertProduto(Produto produto) {
        ProdutoEntity entity = produtoEntityMapper.toEntity(produto);
        produtoPanacheRepository.persist(entity);
    }

    @Override
    public Short getNextCodigoProduto(Short codigoTipoProduto) {
        Integer proximoCodigoProduto = produtoPanacheRepository.getEntityManager()
                .createQuery("""
                        SELECT IFNULL(MAX(codigoProduto)+1, '1')
                        FROM ProdutoEntity
                        WHERE codigoTipoProduto = :codigoTipoProduto
                        """, Integer.class)
                .setParameter("codigoTipoProduto", codigoTipoProduto)
                .getSingleResult();

        return proximoCodigoProduto.shortValue();
    }

    @Override
    public void updateProduto(Produto produto) {
        ProdutoEntity entity = produtoEntityMapper.toEntity(produto);
        produtoPanacheRepository.getEntityManager()
                .merge(entity);
    }

    @Override
    public void deleteProduto(Short codigoProduto, Short codigoTipoProduto) {
        produtoPanacheRepository.delete("""
                FROM ProdutoEntity ali
                WHERE codigoProduto = ?1
                AND codigoTipoProduto = ?2
                """,
                codigoProduto,
                codigoTipoProduto);
    }

    @Override
    public Produto getProdutoById(Short codigoProduto, Short codigoTipoProduto) {
        ProdutoEntityId entityId = new ProdutoEntityId();
        entityId.setCodigoProduto(codigoProduto);
        entityId.setCodigoTipoProduto(codigoTipoProduto);
        ProdutoEntity entityById = produtoPanacheRepository.findById(entityId);
        return produtoEntityMapper.toDomain(entityById);
    }

    @Override
    public List<Produto> getProdutoByTipo(Short codigoTipoProduto) {
        return produtoPanacheRepository
                .find("codigoTipoProduto", codigoTipoProduto)
                .stream()
                .map(produtoEntityMapper::toDomain)
                .toList();
    }

}
