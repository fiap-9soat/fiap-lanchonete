package com.fiap.lanchonete.domain.ports.out;

import java.util.List;

import com.fiap.lanchonete.domain.model.PedidoProduto;

import jakarta.ws.rs.BadRequestException;

public interface PedidoProdutoRepository {
    public void inserir(PedidoProduto pedidoProduto);

    public void checarSeTipoProdutoJaExiste(PedidoProduto pedidoProduto) throws BadRequestException;

    public void remover(PedidoProduto pedidoProduto);

    public void editar(PedidoProduto pedidoProduto);

    List<PedidoProduto> listar();

    List<PedidoProduto> listarPorCodigoPedido(Integer codigoPedido);

    public void removerPorCodigoPedido(Integer codigoPedido);

    public void atualizarPedido(PedidoProduto pedidoProduto);
}
