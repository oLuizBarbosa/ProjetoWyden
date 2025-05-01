package com.wyden.ProjetoWyden.controllers;

import com.wyden.ProjetoWyden.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OcorrenciasController {

    @Autowired
    private HomeRepository or;

    @GetMapping("/ocorrencias")
    public String form(){
        return "Tela principal/tela_de_ocorrencias";
    }

}
