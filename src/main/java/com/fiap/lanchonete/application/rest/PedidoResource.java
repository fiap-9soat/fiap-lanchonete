package com.fiap.lanchonete.application.rest;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.ResponseStatus;

import com.fiap.lanchonete.domain.model.ListaPedido;
import com.fiap.lanchonete.domain.pojo.CreatePedidoDto;
import com.fiap.lanchonete.domain.pojo.MudancaEstadoPedido;
import com.fiap.lanchonete.domain.ports.in.PedidoService;
import com.fiap.lanchonete.domain.ports.out.EstadoPedidoEmitter;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;

@Path("/pedidos")
@AllArgsConstructor
@Tag(name = "Pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    PedidoService pedidoService;

    EstadoPedidoEmitter estadoPedidoEmitter;

    @GET
    @Operation(summary = "Lista os pedidos por ordem de checkout")
    @APIResponses(value = { @APIResponse(responseCode = "200", description = "Lista gerada com sucesso!", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ListaPedido.class)) }),
            @APIResponse(responseCode = "404") })
    public List<ListaPedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @GET
    @Path("/cliente/{codigoCliente}")
    @Operation(summary = "Lista os pedidos feitos por um cliente")
    @APIResponses(value = { @APIResponse(responseCode = "200", description = "Lista gerada com sucesso!", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ListaPedido.class)) }),
            @APIResponse(responseCode = "404") })
    public List<ListaPedido> listarPedidosPorCliente(@PathParam("codigoCliente") Integer codigoCliente) {
        return pedidoService.listarPedidosPorCodigoCliente(codigoCliente);
    }

    @POST
    @Path("/")
    @Operation(summary = "Cria um pedido caso não exista e adiciona os itens nesse pedido.")
    @APIResponse(responseCode = "200", description = "Pedido criado com sucesso!")
    @Transactional(rollbackOn = Exception.class)
    public Integer criarPedido(
            CreatePedidoDto dto) throws Exception {
        return pedidoService.criarPedido(dto);
    }

    @PUT
    @Path("/{codigoPedido}")
    @ResponseStatus(200)
    @Transactional(rollbackOn = Exception.class)
    @APIResponse(responseCode = "200", description = "Pedido editado com sucesso!")
    @Operation(summary = "Edita um pedido. Para alterações de estado, utilizar o recurso /estado.")
    public void editarPedido(@PathParam("codigoPedido") Integer codigoPedido, CreatePedidoDto dto) throws Exception {
        pedidoService.editarPedido(codigoPedido, dto);
    }

    @DELETE
    @Path("/{codigoPedido}")
    @ResponseStatus(200)
    @Transactional(rollbackOn = Exception.class)
    @Operation(summary = "Cancela e apaga o pedido.")
    @APIResponse(responseCode = "200", description = "Pedido deletado com sucesso!")
    public void removerPedido(
            @PathParam("codigoPedido") Integer codigoPedido) throws Exception {
        pedidoService.removerPedido(codigoPedido);
    }

    @POST
    @Path("/estado")
    @ResponseStatus(200)
    @Operation(summary = "Registra um evento de alteração de estado do pedido. Também utilizado para checkout e cancelamento.")
    public void alteraEstadoPedido(@Valid MudancaEstadoPedido dto) {
        estadoPedidoEmitter.emitir(dto);
    }
}
