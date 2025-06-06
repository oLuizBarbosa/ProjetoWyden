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
    private OcorrenciaRepository repository;

    @GetMapping("/nova")
    public String mostrarForm(Model model) {
        OcorrenciaDTO dto = new OcorrenciaDTO();
        dto.setStatus(Ocorrencia.Status.ABERTO); // Status padrão
        model.addAttribute("ocorrenciaDTO", dto);
        model.addAttribute("prioridades", Ocorrencia.Prioridade.values());
        model.addAttribute("fieldErrors", null); // Evita null pointer no FTL
        return "ocorrencias/form";
    }

    @PostMapping
    public String criar(
            @Valid @ModelAttribute("ocorrenciaDTO") OcorrenciaDTO dto,
            BindingResult result,
            @AuthenticationPrincipal Cadastro usuarioLogado,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("prioridades", Ocorrencia.Prioridade.values());
            model.addAttribute("fieldErrors", result.getFieldErrors());
            return "ocorrencias/form";
        }

        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setTitulo(dto.getTitulo());
        ocorrencia.setDescricao(dto.getDescricao());
        ocorrencia.setPrioridade(dto.getPrioridade());
        ocorrencia.setUsuario(usuarioLogado);

        repository.save(ocorrencia);

        redirectAttributes.addFlashAttribute("sucesso",
                "Ocorrência #" + ocorrencia.getId() + " criada com sucesso!");

        return "redirect:/ocorrencias";
    }

    @GetMapping
    public String listar(Model model, @AuthenticationPrincipal Cadastro usuarioLogado) {
        model.addAttribute("ocorrencias", repository.findAll());
        model.addAttribute("usuarioLogado", usuarioLogado); // Para exibir no sidebar
        return "ocorrencias/list";
    }
}