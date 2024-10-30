package com.fiap.lanchonete.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PedidoAlimentoLista {
    Short codigoTipoAlimento;
    Short codigoAlimento;
    Short quantidadeAlimento;
}
