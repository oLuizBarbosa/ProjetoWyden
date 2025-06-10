package com.wyden.ProjetoWyden.DTOs;

import com.wyden.ProjetoWyden.models.Cadastro;
import com.wyden.ProjetoWyden.models.Cadastro.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioInfoDTO {
    private Long id;

    @NotBlank
    private String nome;

    @Email
    private String email;

    @NotNull
    private Role grupo;

    private String telefone;

    public static UsuarioInfoDTO fromEntity(Cadastro cadastro) {
        if (cadastro == null) return null;

        UsuarioInfoDTO dto = new UsuarioInfoDTO();
        dto.setId(cadastro.getId());
        dto.setNome(cadastro.getNome());
        dto.setEmail(cadastro.getEmail());
        dto.setGrupo(cadastro.getGrupo());
        dto.setTelefone(cadastro.getTelefone());
        return dto;
    }
}