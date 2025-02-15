package com.fiap.lanchonete.domain.ports.out;

import com.fiap.lanchonete.domain.model.Produto;

import java.util.List;

public interface ProdutoRepository {
    public void insertProduto(Produto produto);

    public void updateProduto(Produto produto);

    public void deleteProduto(Short codigoProduto, Short codigoTipoProduto);

    /**
     * Recupera o próximo "codigoProduto" possível para inserção na tabela.
     *
     * @param codigoTipoProduto
     * @return
     */
    public Short getNextCodigoProduto(Short codigoTipoProduto);

    public Produto getProdutoById(Short codigoProduto, Short codigoTipoProduto);

    public List<Produto> getProdutoByTipo(Short codigoTipoProduto);
}
