package com.fiap.lanchonete.application.rest.in;

import com.fiap.lanchonete.domain.model.Produto;
import com.fiap.lanchonete.domain.pojo.CreateProdutoDto;
import com.fiap.lanchonete.domain.pojo.DeleteProdutoDto;
import com.fiap.lanchonete.domain.pojo.EditProdutoDto;
import com.fiap.lanchonete.domain.ports.in.ProdutoService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;

@Path("/produtos")
@AllArgsConstructor
@Tag(name = "Produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    ProdutoService produtoService;

    @GET
    @Path("tipo/{codigoTipoProduto}")
    @Operation(summary = "Lista produtos por tipo")
    @APIResponses(value = { @APIResponse(responseCode = "200"), @APIResponse(responseCode = "404") })
    public List<Produto> listarProdutosPorTipo(@PathParam("codigoTipoProduto") Short codigoTipoProduto) {
        return produtoService.listarProdutosPorTipo(codigoTipoProduto);
    }

    @POST
    @ResponseStatus(201)
    @Operation(summary = "Insere um produto na base de dados")
    @APIResponse(responseCode = "201", description = "Produto inserido na base de dados", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateProdutoDto.class)) })
    @Transactional(rollbackOn = Exception.class)
    public void cadastrarProduto(CreateProdutoDto dto) {
        produtoService.cadastrarProduto(dto);
    }

    @PATCH
    @ResponseStatus(200)
    @Operation(summary = "Edita um produto da base de dados")
    @APIResponse(responseCode = "200", description = "Produto editado na base de dados", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = EditProdutoDto.class)) })
    @Transactional(rollbackOn = Exception.class)
    public void editarProduto(EditProdutoDto dto) {
        produtoService.editarProduto(dto);
    }

    @DELETE
    @ResponseStatus(200)
    @Operation(summary = "Deleta um produto da base de dados")
    @APIResponse(responseCode = "200", description = "Produto deletado na base de dados", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = DeleteProdutoDto.class)) })
    @Transactional(rollbackOn = Exception.class)
    public void deletarProduto(DeleteProdutoDto deleteDto) {
        produtoService.deletarProduto(deleteDto);
    }
}
