package com.fiap.lanchonete.domain.model;

import java.time.Instant;

import com.fiap.lanchonete.domain.enums.TipoAlteracao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoPedidoAlimento {
    private Integer codigoPedido;
    private Short codigoTipoAlimento;
    private Short codigoAlimento;
    private Instant tsAlter;
    private Short quantidadeAlimento;
    private TipoAlteracao tipoAlter;
}
