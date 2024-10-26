package com.fiap.lanchonete.application.rest;

import com.fiap.lanchonete.domain.pojo.CreatePedidoDto;
import com.fiap.lanchonete.domain.pojo.MudancaEstadoPedido;
import com.fiap.lanchonete.domain.ports.in.PedidoService;
import com.fiap.lanchonete.domain.ports.out.EstadoPedidoEmitter;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/pedidos")
@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    PedidoService pedidoService;

    EstadoPedidoEmitter estadoPedidoEmitter;

    // @GET
    // @Operation(summary = "Lista os pedidos por ordem crescente")
    // @APIResponses(value = { @APIResponse(responseCode = "200"),
    // @APIResponse(responseCode = "404") })
    // public CreatePedidoDto listarPedidos() {
    // return pedidoService.listarPedidos();
    // }
    @POST
    @Transactional
    public Integer criarPedido(CreatePedidoDto dto) {
        return pedidoService.criarPedido(dto);
    }

    @POST
    @Path("/estado")
    @Operation(summary = "Registra um evento de alteração de estado do pedido.")
    public void alteraEstadoPedido(@Valid MudancaEstadoPedido dto) {
        estadoPedidoEmitter.emitir(dto);
    }
}
