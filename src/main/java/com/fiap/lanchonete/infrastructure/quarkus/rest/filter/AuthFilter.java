package com.fiap.lanchonete.infrastructure.quarkus.rest.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.lanchonete.domain.model.Cliente;
import com.fiap.lanchonete.domain.model.UsuarioAutenticado;
import com.fiap.lanchonete.domain.pojo.CreateClienteDto;
import com.fiap.lanchonete.domain.ports.in.ClienteService;
import com.fiap.lanchonete.infrastructure.quarkus.rest.dto.JwtDto;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

import java.util.Base64;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.jboss.logging.Logger;

@Provider
@RegisterClientHeaders
@Transactional
public class AuthFilter implements ContainerRequestFilter {

    private final Logger logger = Logger.getLogger(AuthFilter.class);

    @Inject
    ClienteService clienteService;

    @Inject
    UsuarioAutenticado usuarioAutenticado;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String token = requestContext.getHeaderString("Authorization");

        if (token == null || token.isEmpty())
            return;

        JwtDto jwt = decodeJwt(token);

        String cpf = jwt.getCustomCpf();
        String email = jwt.getEmail();
        String username = jwt.getCognitoUsername();
        String codigoUsuarioProvedor = jwt.getSub();

        usuarioAutenticado.setCpf(cpf);
        usuarioAutenticado.setEmail(email);
        usuarioAutenticado.setUsername(username);
        usuarioAutenticado.setCodigoUsuarioProvedor(codigoUsuarioProvedor);

        this.processaCadastroCliente();
    }

    public void processaCadastroCliente() {
        try {
            Cliente cliente = clienteService.consultarClientePorCpf(usuarioAutenticado.getCpf());
            usuarioAutenticado.setCodigoCliente(cliente.getCodigoCliente());
            return;
        } catch (NotFoundException ignored) {
        }

        // Usuario não cadastrado
        CreateClienteDto createClienteDto = new CreateClienteDto(
                "",
                "",
                usuarioAutenticado.getCpf(),
                usuarioAutenticado.getEmail());

        int codigoClienteCadastrado = clienteService.cadastrarCliente(createClienteDto);

        usuarioAutenticado.setCodigoCliente(codigoClienteCadastrado);
    }

    /**
     * Atenção: essa função *não* verifica a validade do JWT, já que assumimos que
     * esse processo já foi realizado
     * na camada de API Gateway.
     * 
     * @param jwtString
     * @return
     */
    public JwtDto decodeJwt(String jwtString) {
        DecodedJWT jwt = JWT.decode(jwtString);

        String header = new String(Base64.getUrlDecoder().decode(jwt.getHeader()));
        String payload = new String(Base64.getUrlDecoder().decode(jwt.getPayload()));

        JwtDto jwtPayload = jwtPayloadToDTO(payload);

        logger.info("Header: " + header);
        logger.info("JwtPayload: " + jwtPayload);
        return jwtPayload;
    }

    public JwtDto jwtPayloadToDTO(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(jsonString, JwtDto.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return new JwtDto();
    }
}
