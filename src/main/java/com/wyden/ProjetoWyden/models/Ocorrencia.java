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

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false, name = "data_abertura")
    private LocalDateTime dataAbertura;

    @Column(name = "data_fechamento")
    private LocalDateTime dataFechamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('ABERTO','EM_ANDAMENTO','FECHADO')")
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('BAIXA','MEDIA','ALTA','URGENTE')")
    private Prioridade prioridade;

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