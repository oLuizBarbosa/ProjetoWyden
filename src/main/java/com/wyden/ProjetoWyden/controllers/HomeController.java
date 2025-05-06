package com.wyden.ProjetoWyden.controllers;

import com.wyden.ProjetoWyden.DTOs.HomeDTO;
import com.wyden.ProjetoWyden.repository.OcorrenciaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final OcorrenciaRepository ocorrenciaRepository;

    public HomeController(OcorrenciaRepository ocorrenciaRepository) {
        this.ocorrenciaRepository = ocorrenciaRepository;
    }

    @GetMapping("/home")
    public String home(Authentication authentication, Model model) {
        String email = authentication.getName();

        HomeDTO homeDTO = new HomeDTO();
        homeDTO.setNomeUsuario(email);
        homeDTO.setOcorrencias(ocorrenciaRepository.findByUsuarioEmail(email));

        model.addAttribute("homeDTO", homeDTO);
        return "home";
    }
}