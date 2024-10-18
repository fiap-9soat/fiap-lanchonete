package com.fiap.lanchonete.application.rest;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.reactive.RestPath;

import com.fiap.lanchonete.domain.model.Cliente;
import com.fiap.lanchonete.domain.pojo.CreateClienteDto;
import com.fiap.lanchonete.domain.ports.in.ClienteService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;

@Path("/clientes")
@AllArgsConstructor
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
    public Cliente encontraCliente(@RestPath("cpf") String cpf) {
        return clienteService.consultarClientePorCpf(cpf);
    }

    @POST
    @Operation(summary = "Cadastra o cliente na base de dados")
    @APIResponse(responseCode = "201", description = "Cliente cadastrado com sucesso", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateClienteDto.class)) })
    @Transactional
    public Integer cadastrarCliente(@Valid CreateClienteDto dto) {

        return clienteService.cadastrarCliente(dto);
    }

}
