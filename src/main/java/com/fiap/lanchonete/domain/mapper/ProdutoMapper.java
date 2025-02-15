package com.fiap.lanchonete.domain.mapper;

import com.fiap.lanchonete.domain.model.Produto;
import com.fiap.lanchonete.domain.pojo.CreateProdutoDto;
import com.fiap.lanchonete.domain.pojo.EditProdutoDto;

public interface ProdutoMapper {
    Produto toDomain(CreateProdutoDto createProdutoDto);

    Produto toDomain(EditProdutoDto editProdutoDto);
}
