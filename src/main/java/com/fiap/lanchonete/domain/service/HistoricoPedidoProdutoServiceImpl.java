package com.fiap.lanchonete.domain.service;

import com.fiap.lanchonete.domain.enums.TipoAlteracao;
import com.fiap.lanchonete.domain.mapper.HistoricoPedidoProdutoMapper;
import com.fiap.lanchonete.domain.model.HistoricoPedidoProduto;
import com.fiap.lanchonete.domain.model.PedidoProduto;
import com.fiap.lanchonete.domain.ports.in.HistoricoPedidoProdutoService;
import com.fiap.lanchonete.domain.ports.out.HistoricoPedidoProdutoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HistoricoPedidoProdutoServiceImpl implements HistoricoPedidoProdutoService {
    private final HistoricoPedidoProdutoRepository historicoPedidoProdutoRepository;

    private final HistoricoPedidoProdutoMapper historicoPedidoProdutoMapper;

    @Override
    public void registrarPedidoProduto(PedidoProduto pedidoProduto, TipoAlteracao tipoAlteracao) {
        HistoricoPedidoProduto historicoPedidoProduto = historicoPedidoProdutoMapper
                .fromPedidoProduto(pedidoProduto);
        historicoPedidoProduto.setTipoAlter(tipoAlteracao);
        historicoPedidoProdutoRepository.insert(historicoPedidoProduto);
    }
}
