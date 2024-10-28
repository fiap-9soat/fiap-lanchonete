package com.fiap.lanchonete.domain.ports.out;

import com.fiap.lanchonete.domain.model.PedidoAlimento;

public interface PedidoAlimentoRepository {
    public void inserirAlimentoPedido(PedidoAlimento pedidoAlimento);

    public void checarSeTipoAlimentoJáExiste(PedidoAlimento pedidoAlimento) throws Exception;

    public void removerPedidoAlimento(PedidoAlimento pedidoAlimento);

    public void editarPedidoAlimento(PedidoAlimento pedidoAlimento);
}
