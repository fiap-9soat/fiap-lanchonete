package com.fiap.lanchonete.domain.service;

import com.fiap.lanchonete.domain.model.QrCodeDto;

public interface MetodoPagamentoService {
    QrCodeDto gerarQrCode(String idExterno, Integer codigoPedido);
}
