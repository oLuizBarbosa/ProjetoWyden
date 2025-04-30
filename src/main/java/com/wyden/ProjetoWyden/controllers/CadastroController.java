package com.wyden.ProjetoWyden.controllers;

import com.wyden.ProjetoWyden.DTOs.DTO_Cadastro;
import com.wyden.ProjetoWyden.models.Cadastro;
import com.wyden.ProjetoWyden.repository.CadastroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CadastroController {

    @Autowired
    private CadastroRepository cr;

    @GetMapping ("/cadastrarUsuario")
    public String form(){
        return "cadastro/formCadastro";
    }

    @PostMapping("/cadastrarUsuario")
    public String salvarUsuario(
            @Valid DTO_Cadastro DTOcadastro,  // Recebe o DTO (não a entidade)
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "formCadastro";  // Volta para o formulário com erros
        }

        // Converte DTO para Entidade (Cadastro)
        Cadastro usuario = new Cadastro();
        usuario.setNome(DTOcadastro.getNome());
        usuario.setEmail(DTOcadastro.getEmail());
        // ... (outros campos)

        cr.save(usuario);  // Salva a ENTIDADE no banco
        return "redirect:/login";
    }

}