package com.fiap.lanchonete.application.rest;

import java.util.List;

import jakarta.ws.rs.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.ResponseStatus;

import com.fiap.lanchonete.domain.enums.EstadoPedido;
import com.fiap.lanchonete.domain.model.ListaPedido;
import com.fiap.lanchonete.domain.pojo.CheckoutPedidoDto;
import com.fiap.lanchonete.domain.pojo.CreatePedidoDto;
import com.fiap.lanchonete.domain.pojo.MudancaEstadoPedido;
import com.fiap.lanchonete.domain.ports.in.PedidoService;
import com.fiap.lanchonete.domain.ports.out.EstadoPedidoEmitter;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
    @APIResponses(value = { @APIResponse(responseCode = "200"),
            @APIResponse(responseCode = "404") })
    public List<ListaPedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @GET
    @Path("{codigoCliente}")
    @Operation(summary = "Lista os pedidos feitos por um cliente")
    @APIResponses(value = { @APIResponse(responseCode = "200"),
            @APIResponse(responseCode = "404") })
    public List<ListaPedido> listarPedidosPorCliente(@PathParam("codigoCliente") Integer codigoCliente) {
        return pedidoService.listarPedidosPorCodigoCliente(codigoCliente);
    }

    @POST
    @Path("/")
    @Operation(summary = "Cria um pedido caso não exista e adiciona os itens nesse pedido.")
    @Transactional(rollbackOn = Exception.class)
    public Integer criarPedido(
            CreatePedidoDto dto) throws Exception {
        return pedidoService.criarPedido(dto);
    }

    @PUT
    @Path("/")
    @ResponseStatus(200)
    @Transactional(rollbackOn = Exception.class)
    @Operation(summary = "Edita um pedido.")
    public void editarPedido(
            CreatePedidoDto dto) throws Exception {
        pedidoService.editarPedido(dto);
    }

    @DELETE
    @Path("/")
    @ResponseStatus(200)
    @Transactional(rollbackOn = Exception.class)
    @Operation(summary = "Remove um alimento do pedido.")
    public void removerPedido(
            CreatePedidoDto dto) throws Exception {
        pedidoService.removerPedido(dto);
    }

    @POST
    @Path("/estado")
    @ResponseStatus(200)
    @Operation(summary = "Registra um evento de alteração de estado do pedido.")
    public void alteraEstadoPedido(@Valid MudancaEstadoPedido dto) {
        if (dto.estadoPedido().equals(EstadoPedido.FINALIZADO)) {
            pedidoService.finalizarPedido();
        }
        estadoPedidoEmitter.emitir(dto);
    }

    @PATCH
    @Path("/checkout")
    @ResponseStatus(200)
    @Transactional(rollbackOn = Exception.class)
    @Operation(summary = "Realiza o checkout do cliente para o início do preparo do pedido")
    public void checkoutPedido(CheckoutPedidoDto dto) {
        pedidoService.fazerCheckoutPedido(dto);
        estadoPedidoEmitter.emitir(new MudancaEstadoPedido(dto.getCodigoPedido(), EstadoPedido.RECEBIDO));
    }

}
