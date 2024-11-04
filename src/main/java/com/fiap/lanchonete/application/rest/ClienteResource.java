package com.fiap.lanchonete.application.rest;

import com.fiap.lanchonete.domain.model.Cliente;
import com.fiap.lanchonete.domain.pojo.CreateClienteDto;
import com.fiap.lanchonete.domain.ports.in.ClienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/clientes")
@AllArgsConstructor
@Tag(name = "Clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    ClienteService clienteService;

    @GET
    @Operation(summary = "Lista todos os clientes cadastrados")
    @APIResponse(responseCode = "200", description = "Clientes recuperados com sucesso", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateClienteDto.class)) })
    public List<Cliente> consultaClientes() {
        return clienteService.listarClientes();
    }

    @GET
    @Path("/{cpf}")
    @Operation(summary = "Encontra o cliente por cpf")
    @APIResponse(responseCode = "200", description = "Cliente encontrado", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateClienteDto.class)) })
    public Cliente encontraCliente(@PathParam("cpf") String cpf) {
        return clienteService.consultarClientePorCpf(cpf);
    }

    @POST
    @Operation(summary = "Cadastra o cliente na base de dados")
    @APIResponse(responseCode = "200", description = "Cliente cadastrado com sucesso", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateClienteDto.class)) })
    @Transactional(rollbackOn = Exception.class)
    public Integer cadastrarCliente(@Valid CreateClienteDto dto) {

        return clienteService.cadastrarCliente(dto);
    }

}
