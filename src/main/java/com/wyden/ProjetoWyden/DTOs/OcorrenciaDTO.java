package com.wyden.ProjetoWyden.DTOs;

import com.wyden.ProjetoWyden.models.Cadastro;
import com.wyden.ProjetoWyden.models.Ocorrencia;
import com.wyden.ProjetoWyden.models.Ocorrencia.*;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class OcorrenciaDTO {
    private Long id;

    @NotBlank(message = "Título é obrigatório")
    @Size(max = 200, message = "Título deve ter no máximo 200 caracteres")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @Setter(AccessLevel.NONE) // Bloqueia setter
    private LocalDateTime dataAbertura;

    private LocalDateTime dataFechamento;

    @NotNull(message = "Status é obrigatório")
    private Status status;

    @NotNull(message = "Prioridade é obrigatória")
    private Prioridade prioridade;

    public Ocorrencia toEntity(Cadastro usuario) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setTitulo(this.titulo);
        ocorrencia.setDescricao(this.descricao);
        ocorrencia.setPrioridade(this.prioridade);
        ocorrencia.setStatus(this.status);
        ocorrencia.setUsuario(usuario); // Associa o usuário
        return ocorrencia;
    }
}