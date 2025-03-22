package com.fiap.lanchonete.infrastructure.cognito.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.GetUserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.GetUserResponse;

@ApplicationScoped
public class CognitoClient {
    @ConfigProperty(name = "aws.credentials.access-key")
    String awsAccessKey;
    @ConfigProperty(name = "aws.credentials.secret-key")
    String awsSecretKey;
    @ConfigProperty(name = "aws.credentials.token-key")
    String awsTokenKey;

    CognitoIdentityProviderClient client;

    @PostConstruct
    public void postInit(){
        client = this.createCognitoClient();
    }


    private StaticCredentialsProvider getCredentials() {
        return StaticCredentialsProvider.create(
                AwsSessionCredentials.create(awsAccessKey, awsSecretKey, awsTokenKey));
    }

    private CognitoIdentityProviderClient createCognitoClient() {
        return CognitoIdentityProviderClient
                .builder()
                .credentialsProvider(getCredentials())
                .region(Region.US_EAST_1)
                .build();
    }

    public GetUserResponse getUser(String token){
        var request = GetUserRequest.builder().accessToken(token).build();

        return client.getUser(request);
    }

}
