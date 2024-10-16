package com.fiap.lanchonete.domain.ports.in;

import com.fiap.lanchonete.domain.model.EstadoPedido;
import com.fiap.lanchonete.domain.model.Pedido;

public interface PedidoService {
    public void atualizar(Pedido pedido);

    public void modificarEstado(Integer id, EstadoPedido estadoPedido);
}
