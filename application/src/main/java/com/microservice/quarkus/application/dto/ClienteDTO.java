package com.microservice.quarkus.application.dto;

public record ClienteDTO(
        String nome,
        String sobrenome,
        String email) {
}
