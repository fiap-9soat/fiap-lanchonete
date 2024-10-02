package com.microservice.quarkus.domain.services;

import com.microservice.quarkus.domain.model.Pedido;
import com.microservice.quarkus.domain.ports.out.PedidoRepository;
import com.microservice.quarkus.domain.shared.EstadoPedido;

public class PedidoService {
    
    PedidoRepository pedidoRepository;

    public void alteraEstadoPedido(Pedido pedido, EstadoPedido estadoPedido) {
        pedidoRepository.atualizaEstadoPedido(pedido, estadoPedido);
    }


}
