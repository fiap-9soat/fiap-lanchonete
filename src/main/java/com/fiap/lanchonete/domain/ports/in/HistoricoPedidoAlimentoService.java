package com.fiap.lanchonete.domain.ports.in;

import com.fiap.lanchonete.domain.enums.TipoAlteracao;
import com.fiap.lanchonete.domain.model.PedidoAlimento;

public interface HistoricoPedidoAlimentoService {
    void registrarPedidoAlimento(PedidoAlimento pedidoAlimento, TipoAlteracao tipoAlteracao);
}
