package com.wyden.ProjetoWyden.DTOs;

import com.wyden.ProjetoWyden.models.Cadastro;
import com.wyden.ProjetoWyden.models.Cadastro.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CadastroDTO {
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Formato de email inválido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, max = 100, message = "Senha deve ter 6-100 caracteres")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @NotBlank(message = "Confirmação de senha é obrigatória")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String confirmacaoSenha;

    @NotNull(message = "Tipo de usuário é obrigatório")
    private Role grupo;

    @Size(min = 11, max = 11, message = "Telefone deve ter 11 dígitos")
    @Pattern(regexp = "\\d+", message = "Telefone deve conter apenas números")
    private String telefone;

    @AssertTrue(message = "Senhas não conferem")
    private boolean isSenhasIguais() {
        return senha != null && senha.equals(confirmacaoSenha);
    }

    public Cadastro toEntity() {
        Cadastro cadastro = new Cadastro();
        cadastro.setNome(this.nome);
        cadastro.setEmail(this.email);
        cadastro.setSenha(this.senha);
        cadastro.setGrupo(this.grupo);
        cadastro.setTelefone(this.telefone);
        return cadastro;
    }
}