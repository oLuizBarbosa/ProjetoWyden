package com.wyden.ProjetoWyden.DTOs;

import com.wyden.ProjetoWyden.models.Ocorrencia.Prioridade;
import com.wyden.ProjetoWyden.models.Ocorrencia.Status;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OcorrenciaDTO {

    // Getters e Setters
    private Long id;

    @NotBlank(message = "Título é obrigatório")
    @Size(max = 200, message = "Título deve ter no máximo 200 caracteres")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;

    @NotNull(message = "Status é obrigatório")
    private Status status;

    private Prioridade prioridade;

}
