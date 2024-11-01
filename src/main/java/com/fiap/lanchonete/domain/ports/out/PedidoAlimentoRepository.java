package com.fiap.lanchonete.domain.ports.out;

import java.util.List;

import com.fiap.lanchonete.domain.model.PedidoAlimento;

public interface PedidoAlimentoRepository {
    public void inserir(PedidoAlimento pedidoAlimento);

    public void checarSeTipoAlimentoJaExiste(PedidoAlimento pedidoAlimento) throws Exception;

    public void remover(PedidoAlimento pedidoAlimento);

    public void editar(PedidoAlimento pedidoAlimento);

    List<PedidoAlimento> listar();

    List<PedidoAlimento> listarPorCodigoPedido(Integer codigoPedido);

    public void removerPorCodigoPedido(Integer codigoPedido);
}
