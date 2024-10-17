package com.fiap.lanchonete.application.rest;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.reactive.ResponseStatus;

import com.fiap.lanchonete.application.dto.CreateAlimentoDto;
import com.fiap.lanchonete.application.dto.EditAlimentoDto;
import com.fiap.lanchonete.application.mapper.CreateAlimentoDTOMapper;
import com.fiap.lanchonete.application.mapper.EditAlimentoDTOMapper;
import com.fiap.lanchonete.domain.model.Alimento;
import com.fiap.lanchonete.domain.ports.in.AlimentoService;
import com.fiap.lanchonete.infrastructure.mysql.mapper.AlimentoMapper;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PATCH;
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

    CreateAlimentoDTOMapper createAlimentoDTOMapper;
    EditAlimentoDTOMapper editAlimentoDTOMapper;

    @POST
    @ResponseStatus(201)
    @Operation(summary = "Insere um alimento na base de dados")
    @APIResponse(responseCode = "201", description = "Alimento inserido na base de dados", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateAlimentoDto.class)) })
    @Transactional
    public void cadastrarAlimento(CreateAlimentoDto dto) {
        Alimento alimento = createAlimentoDTOMapper.toDomain(dto);
        alimentoService.cadastrarAlimento(alimento);
    }

    @PATCH
    @ResponseStatus(200)
    @Operation(summary = "Edita um alimento da base de dados")
    @Transactional
    public void editarAlimento(EditAlimentoDto dto) {
        Alimento alimento = editAlimentoDTOMapper.toDomain(dto);
        alimentoService.editarAlimento(alimento);
    }

}
