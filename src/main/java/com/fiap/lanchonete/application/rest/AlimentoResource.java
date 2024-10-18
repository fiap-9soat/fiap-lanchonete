package com.fiap.lanchonete.application.rest;

import com.fiap.lanchonete.domain.pojo.CreateAlimentoDto;
import com.fiap.lanchonete.domain.pojo.DeleteAlimentoDto;
import com.fiap.lanchonete.domain.pojo.EditAlimentoDto;
import com.fiap.lanchonete.domain.ports.in.AlimentoService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.reactive.ResponseStatus;

@Path("/alimentos")
@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlimentoResource {

    AlimentoService alimentoService;

    @POST
    @ResponseStatus(201)
    @Operation(summary = "Insere um alimento na base de dados")
    @APIResponse(responseCode = "201", description = "Alimento inserido na base de dados", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = CreateAlimentoDto.class))})
    @Transactional
    public void cadastrarAlimento(CreateAlimentoDto dto) {
        alimentoService.cadastrarAlimento(dto);
    }

    @PATCH
    @ResponseStatus(200)
    @Operation(summary = "Edita um alimento da base de dados")
    @Transactional
    public void editarAlimento(EditAlimentoDto dto) {
        alimentoService.editarAlimento(dto);
    }

    @DELETE
    @ResponseStatus(200)
    @Operation(summary = "Deleta um alimento da base de dados")
    @Transactional
    public void deletarAlimento(DeleteAlimentoDto deleteDto) {
        alimentoService.deletarAlimento(deleteDto);
    }
}
