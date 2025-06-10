package com.wyden.ProjetoWyden.controllers;

import com.wyden.ProjetoWyden.DTOs.ComentarioDTO;
import com.wyden.ProjetoWyden.models.Comentario;
import com.wyden.ProjetoWyden.models.Ocorrencia;
import com.wyden.ProjetoWyden.services.ComentarioService;
import com.wyden.ProjetoWyden.services.OcorrenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.wyden.ProjetoWyden.models.Cadastro;

@Controller
@RequestMapping("/comentarios")
public class ComentarioController {
    private final ComentarioService comentarioService;
    private final OcorrenciaService ocorrenciaService;

    public ComentarioController(ComentarioService comentarioService, OcorrenciaService ocorrenciaService) {
        this.comentarioService = comentarioService;
        this.ocorrenciaService = ocorrenciaService;
    }

    @PostMapping
    public String criar(
            @Valid @ModelAttribute("comentarioDTO") ComentarioDTO dto,
            BindingResult result,
            @AuthenticationPrincipal Cadastro usuarioLogado,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "redirect:/ocorrencias/" + dto.getOcorrenciaId();
        }

        // Modificação aqui: usar orElseThrow() para tratar Optional<Ocorrencia>
        Ocorrencia ocorrencia = ocorrenciaService.buscarPorId(dto.getOcorrenciaId())
                .orElseThrow(() -> new IllegalArgumentException("Ocorrência não encontrada"));

        Comentario comentario = new Comentario();
        comentario.setTexto(dto.getTexto());
        comentario.setUsuario(usuarioLogado);
        comentario.setOcorrencia(ocorrencia);

        comentarioService.criar(comentario);
        redirectAttributes.addFlashAttribute("sucesso", "Comentário adicionado com sucesso!");
        return "redirect:/ocorrencias/" + dto.getOcorrenciaId();
    }
}