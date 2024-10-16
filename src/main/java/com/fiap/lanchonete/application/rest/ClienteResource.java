package com.fiap.lanchonete.application.rest;


import com.fiap.lanchonete.domain.model.Cliente;
import com.fiap.lanchonete.domain.pojo.CreateClienteDto;
import com.fiap.lanchonete.domain.ports.in.ClienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import lombok.AllArgsConstructor;

import java.util.List;

@Path("/clientes")
@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    ClienteService clienteService;

    @GET
    public List<Cliente> consultaClientes() {
        return clienteService.listarClientes();
    }

    @POST
    @Transactional
    public Integer cadastrarCliente(@Valid CreateClienteDto dto, @Context UriInfo uriInfo) {

        return clienteService.cadastrarCliente(dto);
    }

}
