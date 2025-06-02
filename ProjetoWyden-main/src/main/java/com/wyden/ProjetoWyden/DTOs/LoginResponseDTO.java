package com.wyden.ProjetoWyden.DTOs;

import com.wyden.ProjetoWyden.models.Cadastro.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    // Getters e Setters
    private TokenDTO token;
    private UsuarioInfoDTO usuario;
    private String redirectUrl;

    // Construtores
    public LoginResponseDTO() {}

    public LoginResponseDTO(TokenDTO token, UsuarioInfoDTO usuario, String redirectUrl) {
        this.token = token;
        this.usuario = usuario;
        this.redirectUrl = redirectUrl;
    }

    // Inner Class para o Token

    public static class TokenDTO {
        // Getters
        private String accessToken;
        private String tokenType;
        private long expiresIn;

        public TokenDTO(String accessToken, String tokenType, long expiresIn) {
            this.accessToken = accessToken;
            this.tokenType = tokenType;
            this.expiresIn = expiresIn;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getTokenType() {
            return tokenType;
        }

        public void setTokenType(String tokenType) {
            this.tokenType = tokenType;
        }

        public long getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(long expiresIn) {
            this.expiresIn = expiresIn;
        }
    }

}