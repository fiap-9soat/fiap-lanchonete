package com.fiap.lanchonete.application.rest.out;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import com.fiap.lanchonete.infrastructure.quarkus.rest.dto.ExternalInfoPedidoDto;
import com.fiap.lanchonete.infrastructure.quarkus.rest.dto.ExternalQrCodeDto;
import com.mercadopago.resources.merchantorder.MerchantOrder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
@ApplicationScoped
@RegisterRestClient(configKey = "mercado-pago-api")
@ClientHeaderParam(name = "Authorization", value = "Bearer {authKey}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface MercadoPagoConsumer {

    default String authKey() {
        return ConfigProvider.getConfig().getValue("mercado-pago-api.api-key", String.class);
    }

    @GET
    @Path("merchant_orders/{id}")
    MerchantOrder consultarOrdemPagamento(@RestQuery String idExterno);

    @POST
    @Path("instore/orders/qr/seller/collectors/{idConta}/pos/{idLoja}/qrs")
    ExternalQrCodeDto gerarQrCodePagamento(@RestPath Integer idConta, @RestPath String idLoja,
            ExternalInfoPedidoDto dto);
}
