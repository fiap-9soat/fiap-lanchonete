package com.fiap.lanchonete.domain.ports.in;

import com.fiap.lanchonete.domain.model.Produto;
import com.fiap.lanchonete.domain.pojo.CreateProdutoDto;
import com.fiap.lanchonete.domain.pojo.DeleteProdutoDto;
import com.fiap.lanchonete.domain.pojo.EditProdutoDto;

import java.util.List;

public interface ProdutoService {

    Produto buscarProdutoPorId(Short codigoProduto, Short codigoTipoProduto);

    void cadastrarProduto(CreateProdutoDto createProdutoDto);

    void editarProduto(EditProdutoDto editProdutoDto);

    void deletarProduto(DeleteProdutoDto deleteProdutoDto);

    List<Produto> listarProdutosPorTipo(Short codigoTipoProduto);
}
