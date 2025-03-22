package com.fiap.lanchonete.domain.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class UsuarioAutenticado {
    /**
     * id de cliente interna
     */
    private Integer codigoCliente;
    private String cpf;
    private String email;
    /**
     * Codigo do usuario no provedor de autenticação (ex.: Cognito)
     */
    private String codigoUsuarioProvedor;
    /**
     * Nome de usuario no provedor de autenticação (ex.: Cognito)
     */
    private String username;
}
