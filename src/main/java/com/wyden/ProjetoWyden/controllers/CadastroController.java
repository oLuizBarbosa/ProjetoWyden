package com.wyden.ProjetoWyden.controllers;

import com.wyden.ProjetoWyden.DTOs.DTO_Cadastro;
import com.wyden.ProjetoWyden.models.Cadastro;
import com.wyden.ProjetoWyden.repository.CadastroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CadastroController {

    @Autowired
    private CadastroRepository cr;

    @GetMapping("/cadastrarUsuario")
    public String form(Model model) {
        model.addAttribute("DTO_Cadastro", new DTO_Cadastro());
        return "cadastro/formCadastro";
    }

    @PostMapping("/cadastrarUsuario")
    public String salvarUsuario(
            @Valid @ModelAttribute("DTO_Cadastro") DTO_Cadastro DTOCadastro,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "cadastro/formCadastro"; //
        }

        Cadastro usuario = new Cadastro();
        usuario.setNome(DTOCadastro.getNome());
        usuario.setEmail(DTOCadastro.getEmail());
        usuario.setSenha(DTOCadastro.getSenha());
        usuario.setTelefone(DTOCadastro.getTelefone());
        usuario.setGrupo(DTOCadastro.getGrupo());

        cr.save(usuario);
        return "redirect:/login";
    }
}
