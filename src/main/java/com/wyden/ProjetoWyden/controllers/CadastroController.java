package com.wyden.ProjetoWyden.controllers;

import com.wyden.ProjetoWyden.DTOs.CadastroDTO;
import com.wyden.ProjetoWyden.models.Cadastro;
import com.wyden.ProjetoWyden.models.Cadastro.Role;
import com.wyden.ProjetoWyden.repository.CadastroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cadastros")
public class CadastroController {

    @Autowired
    private CadastroRepository cadastroRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/novo")
    public String mostrarForm(Model model) {
        model.addAttribute("cadastroDTO", new CadastroDTO());
        model.addAttribute("gruposDisponiveis", Role.values()); // Para o select
        model.addAttribute("fieldErrors", null); // Adicionado para evitar null pointer no FTL
        return "cadastros/form";
    }

    @PostMapping
    public String cadastrar(
            @Valid @ModelAttribute("cadastroDTO") CadastroDTO dto,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("fieldErrors", result.getFieldErrors()); // Mapeia erros para o FTL

            return "cadastros/form";
        }

        if (dto.getGrupo() == null) {
            result.rejectValue("grupo", "grupo.vazio", "Selecione um tipo de usuário");
            return "cadastros/form";
        }

        // Verifica se email já existe
        if (cadastroRepository.existsByEmail(dto.getEmail())) {
            result.rejectValue("email", "email.duplicado", "Email já cadastrado");

            return "cadastros/form";
        }

        Cadastro usuario = new Cadastro();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha())); // Criptografa senha
        usuario.setGrupo(dto.getGrupo());
        usuario.setTelefone(dto.getTelefone());

        cadastroRepository.save(usuario);

        redirectAttributes.addFlashAttribute(
                "sucesso",
                "Usuário " + usuario.getNome() + " cadastrado com sucesso!");

        return "redirect:/login";
    }
}