package com.fiap.lanchonete.infrastructure.config.context;

import com.fiap.lanchonete.domain.model.UsuarioAutenticado;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;

/**
 * Classe responsável por "produzir" beans relacionados a models para injeção
 * de dependencia.
 */
@Dependent
public class ModelContext {

    @Produces
    public UsuarioAutenticado usuarioAutenticado(){
        return new UsuarioAutenticado();
    }
}
