package com.wyden.ProjetoWyden.controllers;

import com.wyden.ProjetoWyden.DTOs.LoginDTO;
import com.wyden.ProjetoWyden.DTOs.LoginResponseDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String logout,
            Model model) {

        if (error != null) {
            model.addAttribute("error", "Credenciais inválidas");
        }

        if (logout != null) {
            model.addAttribute("message", "Logout realizado com sucesso");
        }

        // Adiciona o objeto DTO para o formulário
        model.addAttribute("loginDTO", new LoginDTO());

        return "login/formLogin";
    }

    @PostMapping("/login")
    public String processLogin(LoginDTO loginDTO) {
        // A autenticação real é tratada pelo Spring Security
        // Este método só redireciona após o sucesso
        return "redirect:/Home";
    }
}