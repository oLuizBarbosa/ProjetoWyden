package com.wyden.ProjetoWyden.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
public class Comentario {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String texto;

    @Getter
    @Setter
    @NotNull
    @Column(updatable = false)
    private LocalDateTime dataCriacao;

    @Getter
    @Setter
    @NotNull
    @ManyToOne
    @JoinColumn(name = "ocorrencia_id", nullable = false)
    private Ocorrencia ocorrencia;

    @Getter
    @Setter
    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Cadastro usuario;

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
    }

}