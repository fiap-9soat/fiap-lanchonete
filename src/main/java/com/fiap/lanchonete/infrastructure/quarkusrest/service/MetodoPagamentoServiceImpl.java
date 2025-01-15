package com.fiap.lanchonete.infrastructure.quarkusrest.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.fiap.lanchonete.application.rest.out.MercadoPagoConsumer;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.model.QrCodeDto;
import com.fiap.lanchonete.domain.pojo.AlimentoDto;
import com.fiap.lanchonete.domain.ports.in.MetodoPagamentoService;
import com.fiap.lanchonete.domain.ports.out.AlimentoRepository;
import com.fiap.lanchonete.domain.ports.out.PedidoRepository;
import com.fiap.lanchonete.infrastructure.quarkusrest.dto.ExternalInfoPedidoDto;
import com.fiap.lanchonete.infrastructure.quarkusrest.dto.ExternalItemsDto;
import com.fiap.lanchonete.infrastructure.quarkusrest.mapper.QrCodeDTOMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MetodoPagamentoServiceImpl implements MetodoPagamentoService {

    @RestClient
    MercadoPagoConsumer mercadoPagoConsumer;

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    QrCodeDTOMapper qrCodeDTOMapper;

    @Inject
    AlimentoRepository alimentoRepository;

    @ConfigProperty(name = "mercado-pago-api.id-conta")
    Integer idConta;

    @ConfigProperty(name = "mercado-pago-api.id-loja")
    String idLoja;

    @Inject
    Logger logger;

    private static final String TITULO = "LANCHONETE";
    private static final String DESCRICAO = "Pedido feito na lanchonete FIAP";
    private static final String MEDIDA = "unit";

    @ConfigProperty(name = "mercado-pago-api.url-notificacao")
    String urlNotificacao;

    public QrCodeDto gerarQrCode(String idExterno, Pedido pedido, List<AlimentoDto> listaAlimento) {

        List<ExternalItemsDto> listaPedidosExterno = new ArrayList<>();
        listaAlimento.forEach(pedidoAlimento -> {
            var alimento = alimentoRepository.getAlimentoById(pedidoAlimento.getCodigoAlimento(),
                    pedidoAlimento.getCodigoTipoAlimento());
            listaPedidosExterno
                    .add(new ExternalItemsDto(pedidoAlimento.getCodigoTipoAlimento().toString(),
                            alimento.getPrecoAlimento(),
                            pedidoAlimento.getQuantidadeAlimento().intValue(),
                            MEDIDA, alimento.getPrecoAlimento()
                                    .multiply(new BigDecimal(pedidoAlimento
                                            .getQuantidadeAlimento()))));
        });

        BigDecimal valorTotal = listaPedidosExterno.stream()
                .map(valorAlimento -> valorAlimento.total_amount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var req = new ExternalInfoPedidoDto(idExterno, TITULO, DESCRICAO, urlNotificacao,
                valorTotal, listaPedidosExterno);

        logger.info(req);

        var resposta = qrCodeDTOMapper.toDomain(mercadoPagoConsumer.gerarQrCodePagamento(idConta, idLoja, req));
        resposta.setCodigoPedido(pedido.getCodigoPedido());
        return resposta;
    }
}
