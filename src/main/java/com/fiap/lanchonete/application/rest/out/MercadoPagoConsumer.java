package com.fiap.lanchonete.application.rest.out;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import com.fiap.lanchonete.infrastructure.quarkusrest.dto.ExternalInfoPedidoDto;
import com.fiap.lanchonete.infrastructure.quarkusrest.dto.ExternalQrCodeDto;
import com.mercadopago.resources.merchantorder.MerchantOrder;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/")
@RegisterRestClient(configKey = "mercado-pago-api")
@ClientHeaderParam(name = "Authorization", value = "Bearer {mercado-pago-api.api-key}")
public interface MercadoPagoConsumer {

    @GET
    @Path("merchant_orders/search")
    MerchantOrder consultarOrdemPagamento(@RestQuery String external_reference);

    @POST
    @Path("instore/orders/qr/seller/collectors/{idConta}/pos/{idLoja}/qrs")
    ExternalQrCodeDto gerarQrCodePagamento(@RestPath Integer idConta, @RestPath String idLoja,
            ExternalInfoPedidoDto dto);
}
