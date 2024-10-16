package com.fiap.lanchonete.application.rest;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.resteasy.reactive.ResponseStatus;

import com.fiap.lanchonete.application.dto.CreateAlimentoDto;
import com.fiap.lanchonete.application.mapper.AlimentoDTOMapper;
import com.fiap.lanchonete.domain.model.Alimento;
import com.fiap.lanchonete.domain.ports.in.AlimentoService;
import com.fiap.lanchonete.infrastructure.mysql.mapper.AlimentoMapper;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import lombok.AllArgsConstructor;
import jakarta.ws.rs.core.MediaType;

@Path("/alimentos")
@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlimentoResource {

    AlimentoService alimentoService;

    AlimentoDTOMapper alimentoDTOMapper;

    @POST
    @ResponseStatus(201)
    @Operation(summary = "Insere um alimento na base de dados")
    @Transactional
    public void cadastrarAlimento(CreateAlimentoDto dto) {
        Alimento alimento = alimentoDTOMapper.toDomain(dto);
        alimentoService.cadastrarAlimento(alimento);
    }

}
