package com.fiap.lanchonete.application.rest;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.fiap.lanchonete.domain.pojo.CreatePedidoDto;
import com.fiap.lanchonete.domain.ports.in.PedidoService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;

@Path("/pedidos")
@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    PedidoService pedidoService;

    // @GET
    // @Operation(summary = "Lista os pedidos por ordem crescente")
    // @APIResponses(value = { @APIResponse(responseCode = "200"),
    // @APIResponse(responseCode = "404") })
    // public CreatePedidoDto listarPedidos() {
    // return pedidoService.listarPedidos();
    // }
}
