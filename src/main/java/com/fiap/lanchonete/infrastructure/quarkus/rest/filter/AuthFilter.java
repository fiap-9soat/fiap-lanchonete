package com.fiap.lanchonete.infrastructure.quarkus.rest.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.lanchonete.domain.model.Cliente;
import com.fiap.lanchonete.domain.model.UsuarioAutenticado;
import com.fiap.lanchonete.domain.pojo.CreateClienteDto;
import com.fiap.lanchonete.domain.ports.in.ClienteService;
import com.fiap.lanchonete.infrastructure.cognito.service.CognitoClient;
import com.fiap.lanchonete.infrastructure.quarkus.rest.dto.JwtDto;

import io.quarkus.oidc.token.propagation.reactive.AccessTokenRequestReactiveFilter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.GetUserResponse;

import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.LogManager;

@Provider
@RegisterClientHeaders
@Transactional
public class AuthFilter implements ContainerRequestFilter {

    private final Logger logger = Logger.getLogger(AuthFilter.class);

    @Inject
    CognitoClient cognitoClient;

    @Inject
    ClienteService clienteService;

    @Inject
    UsuarioAutenticado usuarioAutenticado;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String token = requestContext.getHeaderString("Authorization");

        JwtDto jwt = decodeJwt(token);

        String cpf = jwt.getCustomCpf();
        String email = jwt.getEmail();
        String username = jwt.getCognitoUsername();
        String codigoUsuario = jwt.getSub();

        usuarioAutenticado.setCpf(cpf);
        usuarioAutenticado.setEmail(email);
        usuarioAutenticado.setUsername(username);
        usuarioAutenticado.setCodigoUsuarioProvedor(codigoUsuario);

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

    public JwtDto decodeJwt(String jwtString) {
        String[] parts = jwtString.split("\\."); // Split into header, payload, and signature
        if (parts.length < 2) {
            logger.info("Invalid JWT format.");
            return new JwtDto();
        }

        String header = new String(Base64.getUrlDecoder().decode(parts[0]));
        String payload = new String(Base64.getUrlDecoder().decode(parts[1]));

        JwtDto jwtPayload = JsonToObjectExample(payload);

        logger.info("Header: " + header);
        logger.info("JwtPayload: " + jwtPayload);
        return jwtPayload;
    }

    public JwtDto JsonToObjectExample(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JwtDto jwtPayload = objectMapper.readValue(jsonString, JwtDto.class);

            return jwtPayload;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JwtDto();
    }
}
