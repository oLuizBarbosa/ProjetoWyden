package com.wyden.ProjetoWyden.controllers;

import com.wyden.ProjetoWyden.DTOs.CadastroDTO;
import com.wyden.ProjetoWyden.models.Cadastro;
import com.wyden.ProjetoWyden.models.Cadastro.Role;
import com.wyden.ProjetoWyden.services.CadastroService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;

@Controller
@RequestMapping("/cadastros")
public class CadastroController {
    private final CadastroService cadastroService;

    public CadastroController(CadastroService cadastroService) {
        this.cadastroService = cadastroService;
    }

    @GetMapping("/novo")
    public String mostrarForm(Model model) {
        model.addAttribute("cadastroDTO", new CadastroDTO());
        model.addAttribute("gruposDisponiveis", Role.values());
        model.addAttribute("fieldErrors", new HashMap<>());
        return "cadastros/form";
    }

    @PostMapping
    public String cadastrar(
            @Valid @ModelAttribute("cadastroDTO") CadastroDTO dto,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors() || dto.getGrupo() == null) {
            model.addAttribute("gruposDisponiveis", Role.values());
            return "cadastros/form";
        }

        try {
            Cadastro novoCadastro = dto.toEntity();
            // Garante que a senha está sendo passada
            novoCadastro.setSenha(dto.getSenha());
            cadastroService.criar(novoCadastro);
            redirectAttributes.addFlashAttribute("sucesso", "Usuário cadastrado com sucesso!");
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("gruposDisponiveis", Role.values());
            return "cadastros/form";
        } catch (ResponseStatusException e) {
            model.addAttribute("erro", e.getReason());
            model.addAttribute("gruposDisponiveis", Role.values());
            model.addAttribute("cadastroDTO", dto);
            return "cadastros/form";
        }
    }
}