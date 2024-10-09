package com.microservice.quarkus.application.rest;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.microservice.quarkus.application.dto.ClienteDTO;
import com.microservice.quarkus.application.service.ClienteServiceImpl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RequestScoped
@Tag(name = "Clientes")
@Path("v1/cliente/")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ClienteResource {

    // @Inject
    // ClienteServiceImpl clienteServiceImpl;

    // @GET
    // @Path("/{CPF}")
    // @Operation(summary = "Consulta os dados de um Cliente através do seu CPF")
    // public void hello(@PathParam("CPF") String cpf, ClienteDTO clienteDTO) {
    // clienteServiceImpl.cadastra(cpf, clienteDTO);
    // }
}
