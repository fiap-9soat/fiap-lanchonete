package com.microservice.quarkus.domain.ports.out;

import java.util.List;

import com.microservice.quarkus.domain.model.EstadoPedido;
import com.microservice.quarkus.domain.model.Pedido;

public interface PedidoRepository {

    List<Pedido> consultaPedidos();

    Pedido findById(Integer codigoPedido);

    void criaPedido(Pedido pedido);

    void atualizaPedido(Pedido pedido);

    void atualizaEstadoPedido(Pedido pedido, EstadoPedido estado);

    void deletaPedido(Pedido pedido);
} 
