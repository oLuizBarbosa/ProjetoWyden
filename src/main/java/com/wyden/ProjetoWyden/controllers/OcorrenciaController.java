package com.wyden.ProjetoWyden.controllers;

import com.wyden.ProjetoWyden.DTOs.OcorrenciaDTO;
import com.wyden.ProjetoWyden.models.Cadastro;
import com.wyden.ProjetoWyden.models.Ocorrencia;
import com.wyden.ProjetoWyden.security.CustomUserDetails;
import com.wyden.ProjetoWyden.services.OcorrenciaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/ocorrencias")
public class OcorrenciaController {
    private final OcorrenciaService ocorrenciaService;

    public OcorrenciaController(OcorrenciaService ocorrenciaService) {
        this.ocorrenciaService = ocorrenciaService;
    }

    @GetMapping("/nova")
    public String mostrarForm(Model model) {
        OcorrenciaDTO dto = new OcorrenciaDTO();
        dto.setStatus(Ocorrencia.Status.ABERTO);
        model.addAttribute("ocorrenciaDTO", dto);
        model.addAttribute("prioridades", Ocorrencia.Prioridade.values());
        return "ocorrencias/form";
    }

    @PostMapping
    public String criar(
            @Valid @ModelAttribute("ocorrenciaDTO") OcorrenciaDTO dto,
            BindingResult result,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("prioridades", Ocorrencia.Prioridade.values());
            return "ocorrencias/form";
        }

        try {
            // Obtém o usuário autenticado
            Cadastro usuario = userDetails.getUsuario();

            // Cria a ocorrência com o usuário associado
            Ocorrencia ocorrencia = dto.toEntity(usuario);
            ocorrenciaService.criar(ocorrencia);

            redirectAttributes.addFlashAttribute("sucesso", "Ocorrência criada com sucesso!");
            return "redirect:/ocorrencias";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao criar ocorrência: " + e.getMessage());
            model.addAttribute("prioridades", Ocorrencia.Prioridade.values());
            return "ocorrencias/form";
        }
    }

    @GetMapping
    public String listar(
            @AuthenticationPrincipal Cadastro usuarioLogado,
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("dataAbertura").descending());
        Page<Ocorrencia> ocorrenciasPage = ocorrenciaService.listarPaginado(pageable);

        // Formata as datas
        ocorrenciasPage.getContent().forEach(ocorrencia -> {
            ocorrencia.setDataFormatada(ocorrencia.getDataAbertura().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        });

        model.addAttribute("ocorrencias", ocorrenciasPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", ocorrenciasPage.getTotalPages());
        model.addAttribute("usuarioLogado", usuarioLogado);

        return "ocorrencias/list";
    }

    @GetMapping("/{id}")
    public String detalhes(
            @PathVariable Long id,
            @AuthenticationPrincipal Cadastro usuarioLogado,
            Model model) {

        Ocorrencia ocorrencia = ocorrenciaService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ocorrência não encontrada"));

        // Formata as datas
        ocorrencia.setDataFormatada(ocorrencia.getDataAbertura().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        if (ocorrencia.getDataFechamento() != null) {
            ocorrencia.setDataFechamentoFormatada(ocorrencia.getDataFechamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        }

        model.addAttribute("ocorrencia", ocorrencia);
        model.addAttribute("usuarioLogado", usuarioLogado);

        return "ocorrencias/detalhes"; // Crie este template (detalhes.ftl)
    }

    @PostMapping("/{id}/fechar")
    public String fecharOcorrencia(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        ocorrenciaService.atualizarStatus(id, Ocorrencia.Status.FECHADO);
        redirectAttributes.addFlashAttribute("sucesso", "Ocorrência fechada com sucesso!");
        return "redirect:/ocorrencias/" + id;
    }
}
