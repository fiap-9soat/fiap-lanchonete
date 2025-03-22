package com.fiap.lanchonete.infrastructure.quarkus.rest.filter;

import com.fiap.lanchonete.domain.model.Cliente;
import com.fiap.lanchonete.domain.model.UsuarioAutenticado;
import com.fiap.lanchonete.domain.pojo.CreateClienteDto;
import com.fiap.lanchonete.domain.ports.in.ClienteService;
import com.fiap.lanchonete.infrastructure.cognito.service.CognitoClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.GetUserResponse;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.LogManager;

@ApplicationScoped
public class AuthFilter {

    private final Logger logger = Logger.getLogger(AuthFilter.class);

    @Inject
    CognitoClient cognitoClient;

    @Inject
    ClienteService clienteService;

    @Inject
    UsuarioAutenticado usuarioAutenticado;


    private String getAttributeValue(List<AttributeType> attributes, String attributeName){
        for (AttributeType attributeType : attributes){
            var fieldName = attributeType.getValueForField("Name", String.class).orElse(null);
            if (Objects.equals(fieldName, attributeName)){
                return attributeType.getValueForField("Value", String.class).orElse(null);
            }
        }

        return null;
    }

    @ServerRequestFilter(preMatching = true)
    public Optional<Response> authFilter(ContainerRequestContext requestContext){
        String authorization = requestContext.getHeaderString("Authorization");

        if (authorization == null){
            return Optional.empty();
        }

        GetUserResponse getUserResponse = cognitoClient.getUser(authorization);
        var attributes = getUserResponse.userAttributes();

        String username = getUserResponse.username();
        String cpf = getAttributeValue(attributes, "custom:cpf");
        String email = getAttributeValue(attributes, "email");
        String codigoUsuario = getAttributeValue(attributes, "sub");

        usuarioAutenticado.setCpf(cpf);
        usuarioAutenticado.setEmail(email);
        usuarioAutenticado.setUsername(username);
        usuarioAutenticado.setCodigoUsuarioProvedor(codigoUsuario);

        this.processaCadastroCliente();

        return Optional.empty();

    }

    public void processaCadastroCliente(){
        try {
            Cliente cliente = clienteService.consultarClientePorCpf(usuarioAutenticado.getCpf());
            usuarioAutenticado.setCodigoCliente(cliente.getCodigoCliente());
            return;
        } catch (NotFoundException ignored){}

        // Usuario não cadastrado
        CreateClienteDto createClienteDto = new CreateClienteDto(
                "",
                "",
                usuarioAutenticado.getCpf(),
                usuarioAutenticado.getEmail()
        );

        int codigoClienteCadastrado = clienteService.cadastrarCliente(createClienteDto);

        usuarioAutenticado.setCodigoCliente(codigoClienteCadastrado);
    }

}
