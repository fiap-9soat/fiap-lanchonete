package com.fiap.lanchonete.application.rest;


import com.fiap.lanchonete.application.dto.CreateClienteDto;
import com.fiap.lanchonete.application.mapper.ClienteDTOMapper;
import com.fiap.lanchonete.domain.model.Cliente;
import com.fiap.lanchonete.domain.ports.in.ClienteService;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.AllArgsConstructor;

import java.net.URI;

@Path("/clientes")
@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    ClienteService clienteService;

    ClienteDTOMapper clienteDTOMapper;

    @POST
    public Response cadastrarCliente(@Valid CreateClienteDto dto, @Context UriInfo uriInfo) {
        Cliente cliente = clienteDTOMapper.toDomain(dto);
        int codigoCliente = clienteService.cadastrarCliente(cliente);

        URI location = uriInfo.getAbsolutePathBuilder().path(Integer.toString(codigoCliente)).build();
        return Response.created(location).build();
    }

}
