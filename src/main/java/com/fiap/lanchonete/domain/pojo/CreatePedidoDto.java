package com.fiap.lanchonete.domain.pojo;

import java.util.List;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePedidoDto {
    @Nullable
    private Integer codigoCliente;
    List<AlimentoDto> listaAlimentos;
}
