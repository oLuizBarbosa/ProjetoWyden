package com.wyden.ProjetoWyden.DTOs;

import com.wyden.ProjetoWyden.models.Cadastro.Role;
import lombok.Getter;
import lombok.Setter;

// Getters e Setters
@Getter
@Setter
public class UsuarioInfoDTO {
    private Long id;
    private String nome;
    private String email;
    private Role grupo;
    private String telefone;

}