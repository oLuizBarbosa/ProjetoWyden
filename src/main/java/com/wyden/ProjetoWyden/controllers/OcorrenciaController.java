package com.wyden.ProjetoWyden.controllers;

import com.wyden.ProjetoWyden.DTOs.OcorrenciaDTO;
import com.wyden.ProjetoWyden.models.*;
import com.wyden.ProjetoWyden.repository.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @GetMapping("/nova")
    public String mostrarForm(Model model) {
        model.addAttribute("ocorrenciaDTO", new OcorrenciaDTO());
        model.addAttribute("prioridades", Ocorrencia.Prioridade.values());
        return "ocorrencias/form";
    }

    @PostMapping
    public String criar(
            @Valid @ModelAttribute("ocorrenciaDTO") OcorrenciaDTO dto,
            BindingResult result,
            @AuthenticationPrincipal Cadastro usuarioLogado,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "ocorrencias/form";
        }

        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setTitulo(dto.getTitulo());
        ocorrencia.setDescricao(dto.getDescricao());
        ocorrencia.setPrioridade(dto.getPrioridade());
        ocorrencia.setUsuario(usuarioLogado); // Associa automaticamente

        ocorrenciaRepository.save(ocorrencia);

        redirectAttributes.addFlashAttribute(
                "sucesso",
                "Ocorrência #" + ocorrencia.getId() + " criada com sucesso!");

        return "redirect:/ocorrencias";
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ocorrencias", ocorrenciaRepository.findAll());
        return "ocorrencias/lista";
    }
}