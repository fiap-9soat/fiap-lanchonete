package com.fiap.lanchonete.infrastructure.quarkusrest.service;

import java.math.BigDecimal;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.fiap.lanchonete.application.rest.out.MercadoPagoConsumer;
import com.fiap.lanchonete.domain.model.QrCodeDto;
import com.fiap.lanchonete.domain.ports.out.PedidoRepository;
import com.fiap.lanchonete.domain.ports.in.MetodoPagamentoService;
import com.fiap.lanchonete.infrastructure.quarkusrest.dto.ExternalInfoPedidoDto;
import com.fiap.lanchonete.infrastructure.quarkusrest.dto.ExternalItemsDto;
import com.fiap.lanchonete.infrastructure.quarkusrest.mapper.QrCodeDTOMapper;

import jakarta.inject.Inject;

@ApplicationScoped
public class MetodoPagamentoServiceImpl implements MetodoPagamentoService {

    @RestClient
    MercadoPagoConsumer mercadoPagoConsumer;

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    QrCodeDTOMapper qrCodeDTOMapper;

    @ConfigProperty(name = "mercado-pago-api.id-conta")
    Integer idConta;

    @ConfigProperty(name = "mercado-pago-api.id-loja")
    String idLoja;

    private static final String TITULO = "LANCHONETE";
    private static final String DESCRICAO = "Pedido feito na lanchonete FIAP";
    private static final String MEDIDA = "unit";

    @ConfigProperty(name = "mercado-pago-api.url-notificacao")
    String urlNotificacao;

    public QrCodeDto gerarQrCode(String idExterno, Integer codigoPedido) {
        var valorTotal = new BigDecimal(0.00);

        var listaPedidos = pedidoRepository.buscarPedidosPorCodigoPedido(codigoPedido);
        var listaPedidosExterno = listaPedidos.getFirst().getListaPedidoAlimentos().stream()
                .map(pedidoAlimento -> {
                    return new ExternalItemsDto(pedidoAlimento.getCodigoTipoAlimento().toString(),
                            pedidoAlimento.getValorAlimento(), pedidoAlimento.getQuantidadeAlimento().intValue(),
                            MEDIDA, valorTotal);
                }).toList();

        var req = new ExternalInfoPedidoDto(idExterno, TITULO, DESCRICAO, urlNotificacao,
                valorTotal, listaPedidosExterno);

        var resposta = qrCodeDTOMapper.toDomain(mercadoPagoConsumer.gerarQrCodePagamento(idConta, idLoja, req));
        resposta.setCodigoPedido(codigoPedido);
        return resposta;
    }
}
