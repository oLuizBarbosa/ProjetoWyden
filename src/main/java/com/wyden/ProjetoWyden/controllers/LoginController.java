package com.wyden.ProjetoWyden.controllers;

import com.wyden.ProjetoWyden.DTOs.LoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String mostrarForm(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String logout,
            Model model) {

        if (error != null) {
            model.addAttribute("erro", "Email ou senha inválidos");
        }

        if (logout != null) {
            model.addAttribute("sucesso", "Logout realizado com sucesso");
        }

        model.addAttribute("loginDTO", new LoginDTO());
        return "login/form";
    }

    // O Spring Security cuidará do POST /login
}