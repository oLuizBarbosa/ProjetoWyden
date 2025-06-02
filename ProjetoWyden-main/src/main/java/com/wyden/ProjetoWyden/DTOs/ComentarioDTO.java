package com.wyden.ProjetoWyden.DTOs;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioDTO {

    // Getters e Setters
    private Long id;

    @NotBlank(message = "Texto é obrigatório")
    private String texto;

    @NotNull(message = "ID da ocorrência é obrigatório")
    private Long ocorrenciaId;

}
