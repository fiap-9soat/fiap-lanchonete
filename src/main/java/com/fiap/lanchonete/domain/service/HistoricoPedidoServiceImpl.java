package com.fiap.lanchonete.domain.service;

import com.fiap.lanchonete.domain.mapper.HistoricoPedidoMapper;
import com.fiap.lanchonete.domain.model.HistoricoPedido;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.ports.in.HistoricoPedidoService;
import com.fiap.lanchonete.domain.ports.out.HistoricoPedidoRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class HistoricoPedidoServiceImpl implements HistoricoPedidoService {

    private final HistoricoPedidoRepository historicoPedidoRepository;

    private final HistoricoPedidoMapper historicoPedidoMapper;

    @Override
    public void registrarPedido(Pedido pedido) {
        HistoricoPedido historicoPedido = historicoPedidoMapper.fromPedido(pedido);
        historicoPedidoRepository.insert(historicoPedido);
    }

    @Override
    public List<HistoricoPedido> consultarPorCodigoPedido(Integer codigoPedido) {
        return historicoPedidoRepository.findByCodigoPedido(codigoPedido);
    }
}
