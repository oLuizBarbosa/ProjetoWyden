package com.wyden.ProjetoWyden.DTOs;

public class LoginResponseDTO {
    private String token;
    private String redirectUrl;

    // Construtores
    public LoginResponseDTO() {}

    public LoginResponseDTO(String token, String redirectUrl) {
        this.token = token;
        this.redirectUrl = redirectUrl;
    }

    // Getters e Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}