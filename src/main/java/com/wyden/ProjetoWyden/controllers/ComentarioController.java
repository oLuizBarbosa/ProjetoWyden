package com.wyden.ProjetoWyden.controllers;

import com.wyden.ProjetoWyden.DTOs.ComentarioDTO;
import com.wyden.ProjetoWyden.models.*;
import com.wyden.ProjetoWyden.repository.ComentarioRepository;
import com.wyden.ProjetoWyden.repository.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @PostMapping
    public String criar(
            @Valid @ModelAttribute("comentarioDTO") ComentarioDTO dto,
            BindingResult result,
            @AuthenticationPrincipal Cadastro usuarioLogado,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "redirect:/ocorrencias/" + dto.getOcorrenciaId(); // Volta para a ocorrência
        }

        Comentario comentario = new Comentario();
        comentario.setTexto(dto.getTexto());
        comentario.setUsuario(usuarioLogado);

        Ocorrencia ocorrencia = ocorrenciaRepository.findById(dto.getOcorrenciaId())
                .orElseThrow(() -> new IllegalArgumentException("Ocorrência inválida"));
        comentario.setOcorrencia(ocorrencia);

        comentarioRepository.save(comentario);

        redirectAttributes.addFlashAttribute(
                "sucesso",
                "Comentário adicionado!");

        return "redirect:/ocorrencias/" + dto.getOcorrenciaId();
    }
}
