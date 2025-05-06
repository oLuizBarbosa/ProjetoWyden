package com.wyden.ProjetoWyden.DTOs;

import com.wyden.ProjetoWyden.models.Ocorrencia;
import java.util.List;

public class HomeDTO {
    private List<Ocorrencia> ocorrencias;
    private String nomeUsuario;

    public List<Ocorrencia> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(List<Ocorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
}