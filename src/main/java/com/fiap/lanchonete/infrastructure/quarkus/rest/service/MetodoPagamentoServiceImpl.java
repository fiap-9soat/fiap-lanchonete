package com.fiap.lanchonete.infrastructure.quarkus.rest.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.fiap.lanchonete.application.rest.out.MercadoPagoConsumer;
import com.fiap.lanchonete.domain.model.Pedido;
import com.fiap.lanchonete.domain.model.QrCodeDto;
import com.fiap.lanchonete.domain.pojo.ProdutoDto;
import com.fiap.lanchonete.domain.ports.in.MetodoPagamentoService;
import com.fiap.lanchonete.domain.ports.out.ProdutoRepository;
import com.fiap.lanchonete.domain.ports.out.PedidoRepository;
import com.fiap.lanchonete.infrastructure.quarkus.rest.dto.ExternalInfoPedidoDto;
import com.fiap.lanchonete.infrastructure.quarkus.rest.dto.ExternalItemsDto;
import com.fiap.lanchonete.infrastructure.quarkus.rest.mapper.QrCodeDTOMapper;

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
    ProdutoRepository produtoRepository;

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

    public QrCodeDto gerarQrCode(String idExterno, Pedido pedido, List<ProdutoDto> listaProduto) {

        List<ExternalItemsDto> listaPedidosExterno = new ArrayList<>();
        listaProduto.forEach(pedidoProduto -> {
            var produto = produtoRepository.getProdutoById(pedidoProduto.getCodigoProduto(),
                    pedidoProduto.getCodigoTipoProduto());
            listaPedidosExterno
                    .add(new ExternalItemsDto(pedidoProduto.getCodigoTipoProduto().toString(),
                            produto.getPrecoProduto(),
                            pedidoProduto.getQuantidadeProduto().intValue(),
                            MEDIDA, produto.getPrecoProduto()
                                    .multiply(new BigDecimal(pedidoProduto
                                            .getQuantidadeProduto()))));
        });

        BigDecimal valorTotal = listaPedidosExterno.stream()
                .map(valorProduto -> valorProduto.total_amount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var req = new ExternalInfoPedidoDto(idExterno, TITULO, DESCRICAO, urlNotificacao,
                valorTotal, listaPedidosExterno);

        logger.info(req);

        var resposta = qrCodeDTOMapper.toDomain(mercadoPagoConsumer.gerarQrCodePagamento(idConta, idLoja, req));
        resposta.setCodigoPedido(pedido.getCodigoPedido());
        return resposta;
    }
}
