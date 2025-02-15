package com.fiap.lanchonete.domain.service;

import com.fiap.lanchonete.domain.mapper.ProdutoMapper;
import com.fiap.lanchonete.domain.model.Produto;
import com.fiap.lanchonete.domain.pojo.CreateProdutoDto;
import com.fiap.lanchonete.domain.pojo.DeleteProdutoDto;
import com.fiap.lanchonete.domain.pojo.EditProdutoDto;
import com.fiap.lanchonete.domain.ports.in.ProdutoService;
import com.fiap.lanchonete.domain.ports.out.ProdutoRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private ProdutoRepository produtoRepository;

    private ProdutoMapper produtoMapper;

    @Override
    public Produto buscarProdutoPorId(Short codigoProduto, Short codigoTipoProduto) {
        return produtoRepository.getProdutoById(codigoProduto, codigoTipoProduto);
    }

    @Override
    public void cadastrarProduto(CreateProdutoDto createProdutoDto) {
        Short lastCodigoProduto = produtoRepository.getNextCodigoProduto(createProdutoDto.codigoTipoProduto());
        Produto produto = produtoMapper.toDomain(createProdutoDto);
        produto.setCodigoProduto(lastCodigoProduto);

        produtoRepository.insertProduto(produto);
    }

    @Override
    public void editarProduto(EditProdutoDto editProdutoDto) {
        Produto produtoById = produtoRepository.getProdutoById(editProdutoDto.codigoProduto(),
                editProdutoDto.codigoTipoProduto());
        if (Objects.isNull(produtoById)) {
            throw new NotFoundException("Produto não encontrado!");
        }

        Produto produto = produtoMapper.toDomain(editProdutoDto);
        produtoRepository.updateProduto(produto);
    }

    @Override
    public void deletarProduto(DeleteProdutoDto deleteProdutoDto) {
        Produto produtoById = produtoRepository.getProdutoById(deleteProdutoDto.codigoProduto(),
                deleteProdutoDto.codigoTipoProduto());
        if (Objects.isNull(produtoById)) {
            throw new NotFoundException("Produto não encontrado!");
        }
        produtoRepository.deleteProduto(deleteProdutoDto.codigoProduto(), deleteProdutoDto.codigoTipoProduto());
    }

    @Override
    public List<Produto> listarProdutosPorTipo(Short codigoTipoProduto) {
        return produtoRepository.getProdutoByTipo(codigoTipoProduto);
    }
}
