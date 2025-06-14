package com.wyden.ProjetoWyden.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String mostrarForm(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String logout,
            Model model) {

        if (error != null) {
            model.addAttribute("erro", "Email ou senha inválidos"); // Use messageSource em produção
        }

        if (logout != null) {
            model.addAttribute("sucesso", "Logout realizado com sucesso");
        }

        return "login/form";
    }
}