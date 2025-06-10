package com.wyden.ProjetoWyden.DTOs;

import com.wyden.ProjetoWyden.models.Cadastro;
import com.wyden.ProjetoWyden.models.Comentario;
import com.wyden.ProjetoWyden.models.Ocorrencia;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ComentarioDTO {

    @NotBlank(message = "Texto é obrigatório")
    @Size(max = 500, message = "Comentário deve ter no máximo 500 caracteres")
    private String texto;

    @NotNull(message = "Ocorrência é obrigatória")
    @Positive(message = "ID da ocorrência inválido")
    private Long ocorrenciaId;

    public Comentario toEntity(Cadastro usuario, Ocorrencia ocorrencia) {
        Comentario comentario = new Comentario();
        comentario.setTexto(this.texto);
        comentario.setUsuario(usuario);
        comentario.setOcorrencia(ocorrencia);
        return comentario;
    }
}