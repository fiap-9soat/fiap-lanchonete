package com.fiap.lanchonete.domain.service;

import com.fiap.lanchonete.domain.enums.TipoAlteracao;
import com.fiap.lanchonete.domain.mapper.HistoricoPedidoAlimentoMapper;
import com.fiap.lanchonete.domain.model.HistoricoPedidoAlimento;
import com.fiap.lanchonete.domain.model.PedidoAlimento;
import com.fiap.lanchonete.domain.ports.in.HistoricoPedidoAlimentoService;
import com.fiap.lanchonete.domain.ports.out.HistoricoPedidoAlimentoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HistoricoPedidoAlimentoServiceImpl implements HistoricoPedidoAlimentoService {
    private final HistoricoPedidoAlimentoRepository historicoPedidoAlimentoRepository;

    private final HistoricoPedidoAlimentoMapper historicoPedidoAlimentoMapper;

    @Override
    public void registrarPedidoAlimento(PedidoAlimento pedidoAlimento) {
        HistoricoPedidoAlimento historicoPedidoAlimento = historicoPedidoAlimentoMapper
                .fromPedidoAlimento(pedidoAlimento);
        historicoPedidoAlimento.setTipoAlter(TipoAlteracao.I);
        historicoPedidoAlimentoRepository.insert(historicoPedidoAlimento);
    }
}
