package com.fiap.lanchonete.domain.ports.in;

import com.fiap.lanchonete.domain.model.HistoricoPedido;
import com.fiap.lanchonete.domain.model.Pedido;

import java.util.List;

public interface HistoricoPedidoService {
    /**
     * Adiciona o pedido conforme modelo diretamente no histórico
     * @param pedido
     */
    void registrarPedido(Pedido pedido, Short quantidadeAlimentos);

    List<HistoricoPedido> consultarPorCodigoPedido(Integer codigoPedido);
}
