package com.wyden.ProjetoWyden.controllers;

import com.wyden.ProjetoWyden.models.Cadastro;
import com.wyden.ProjetoWyden.repository.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CadastroController {

    @Autowired
    private CadastroRepository cr;

    @GetMapping (value ="/cadastrarUsuario")
    public String form(){
        return "cadastro/formCadastro";
    }

    @PostMapping(value ="/cadastrarUsuario")
    public String form(Cadastro usuario){

        cr.save(usuario);

        return "redirect:/login";
    }

}