package com.fiap.lanchonete.application.rest;

import com.fiap.lanchonete.domain.pojo.CreatePedidoDto;
import com.fiap.lanchonete.domain.pojo.MudancaEstadoPedido;
import com.fiap.lanchonete.domain.ports.in.PedidoService;
import com.fiap.lanchonete.domain.ports.out.EstadoPedidoEmitter;

import io.smallrye.common.constraint.Nullable;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;

@Path("/pedidos")
@AllArgsConstructor
@Tag(name = "Pedidos")
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
    @Path("/")
    @Operation(summary = "Cria um pedido caso não existe e adiciona os itens nesse pedido.")
    @Transactional
    public Integer criarPedido(
            CreatePedidoDto dto) throws Exception {
        return pedidoService.criarPedido(dto);
    }

    @PUT
    @Path("/")
    @ResponseStatus(200)
    @Operation(summary = "Edita um pedido.")
    @Transactional
    public void editarPedido(
            CreatePedidoDto dto) throws Exception {
        pedidoService.editarPedido(dto);
    }

    @DELETE
    @Path("/")
    @ResponseStatus(200)
    @Operation(summary = "Remove um alimento do pedido.")
    @Transactional
    public void removerPedido(
            CreatePedidoDto dto) throws Exception {
        pedidoService.removerPedido(dto);
    }

    @POST
    @Path("/estado")
    @ResponseStatus(200)
    @Operation(summary = "Registra um evento de alteração de estado do pedido.")
    public void alteraEstadoPedido(@Valid MudancaEstadoPedido dto) {
        estadoPedidoEmitter.emitir(dto);
    }

}
