package com.wyden.ProjetoWyden.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.wyden.ProjetoWyden.models.Cadastro.Role;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroDTO {

    // Getters e Setters
    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String senha;

    @NotNull(message = "O grupo é obrigatório")
    private Role grupo;

    @Size(min = 11, max = 11, message = "Telefone deve ter 11 dígitos")
    @Pattern(regexp = "\\d{11}", message = "Apenas números são permitidos")
    private String telefone;

}