package com.fiap.lanchonete.infrastructure.quarkus.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDto {
    private String sub;
    private String iss;

    @JsonProperty("cognito:username")
    private String cognitoUsername;

    @JsonProperty("origin_jti")
    private String originJti;

    private String aud;

    @JsonProperty("event_id")
    private String eventId;

    @JsonProperty("token_use")
    private String tokenUse;

    @JsonProperty("auth_time")
    private long authTime;
    private long exp;

    @JsonProperty("custom:cpf")
    private String customCpf;

    private long iat;
    private String jti;
    private String email;

    // Getters and setters
    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getCognitoUsername() {
        return cognitoUsername;
    }

    public void setCognitoUsername(String cognitoUsername) {
        this.cognitoUsername = cognitoUsername;
    }

    public String getOriginJti() {
        return originJti;
    }

    public void setOriginJti(String originJti) {
        this.originJti = originJti;
    }

    public String getAud() {
        return aud;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getTokenUse() {
        return tokenUse;
    }

    public void setTokenUse(String tokenUse) {
        this.tokenUse = tokenUse;
    }

    public long getAuthTime() {
        return authTime;
    }

    public void setAuthTime(long authTime) {
        this.authTime = authTime;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public String getCustomCpf() {
        return customCpf;
    }

    public void setCustomCpf(String customCpf) {
        this.customCpf = customCpf;
    }

    public long getIat() {
        return iat;
    }

    public void setIat(long iat) {
        this.iat = iat;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "JwtPayload{" +
                "sub='" + sub + '\'' +
                ", iss='" + iss + '\'' +
                ", cognitoUsername='" + cognitoUsername + '\'' +
                ", originJti='" + originJti + '\'' +
                ", aud='" + aud + '\'' +
                ", eventId='" + eventId + '\'' +
                ", tokenUse='" + tokenUse + '\'' +
                ", authTime=" + authTime +
                ", exp=" + exp +
                ", customCpf='" + customCpf + '\'' +
                ", iat=" + iat +
                ", jti='" + jti + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
