package com.wyden.ProjetoWyden.controllers;

import com.wyden.ProjetoWyden.models.Ocorrencia;
import com.wyden.ProjetoWyden.repository.OcorrenciasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OcorrenciasController {

    @Autowired
    private OcorrenciasRepository or;

    @GetMapping("/ocorrencias")
    public String form(){
        return "Tela principal/tela_de_ocorrencias";
    }

}
