package com.wyden.ProjetoWyden.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor // Adicionado para desserialização
public class LoginResponseDTO {
    private UsuarioInfoDTO usuario;
    private String redirectUrl;
    private String mensagem;

    public LoginResponseDTO(UsuarioInfoDTO usuario, String redirectUrl) {
        this.usuario = usuario;
        this.redirectUrl = redirectUrl;
        this.mensagem = "Login realizado com sucesso";
    }

    public LoginResponseDTO(String mensagem) {
        this.mensagem = mensagem;
        this.redirectUrl = "/login";
    }
}