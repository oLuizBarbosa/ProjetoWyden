// src/main/java/com/wyden/ProjetoWyden/models/Ocorrencia.java
package com.wyden.ProjetoWyden.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Ocorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private String status;
    private LocalDateTime dataAbertura;
    private String usuarioEmail; // Email do usuário vinculado

    // Construtores e getters/setters
    public Ocorrencia() {
        this.dataAbertura = LocalDateTime.now();
        this.status = "Aberto";
    }
}