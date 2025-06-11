package com.wyden.ProjetoWyden.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Ocorrencia {


    @Getter
    public enum Status {
        ABERTO("Em Aberto"),
        EM_ANDAMENTO("Em Andamento"),
        FECHADO("Fechado");

        private final String descricao;
        Status(String descricao) { this.descricao = descricao; }
    }

    public enum Prioridade {
        BAIXA, MEDIA, ALTA, URGENTE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 200)
    @Column(nullable = false)
    private String titulo;

    @NotBlank
    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @NotNull
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataAbertura;

    @Column
    private LocalDateTime dataFechamento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Prioridade prioridade;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Cadastro usuario;

    @OneToMany(mappedBy = "ocorrencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.dataAbertura = LocalDateTime.now();
        this.status = Status.ABERTO;
    }

    // Getters e Setters
    @Transient
    private String dataFormatada;

    @Transient
    private String dataFechamentoFormatada;

    // Métodos de negócio
    public void fechar() {
        if (this.status != Status.FECHADO) {
            this.status = Status.FECHADO;
            this.dataFechamento = LocalDateTime.now();
        }
    }

    public void reabrir() {
        if (this.status == Status.FECHADO) {
            this.status = Status.ABERTO;
            this.dataFechamento = null;
        }
    }
}