package com.fiap.lanchonete.domain.ports.in;

import java.util.List;

import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.model.QrCodeDto;
import com.fiap.lanchonete.domain.pojo.AlimentoDto;

public interface MetodoPagamentoService {
    QrCodeDto gerarQrCode(String idExterno, Pedido pedido, List<AlimentoDto> listaPedidoAlimento);
}
