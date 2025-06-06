package com.wyden.ProjetoWyden.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Ocorrencia {
    // Enums para status e prioridade (adicionados como inner classes)
    public enum Status {
        ABERTO, EM_ANDAMENTO, FECHADO
    }

    public enum Prioridade {
        BAIXA, MEDIA, ALTA, URGENTE
    }

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotBlank(message = "O título é obrigatório")
    @Size(max = 200)
    @Column(nullable = false)
    private String titulo;

    @Getter
    @Setter
    @NotBlank(message = "A descrição é obrigatória")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Getter
    @Setter
    @NotNull
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataAbertura;

    @Getter
    @Column
    private LocalDateTime dataFechamento;

    @Getter
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @Getter
    @Setter
    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Cadastro usuario;

    @Getter
    @Setter
    @OneToMany(mappedBy = "ocorrencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios;


    // Construtor que inicializa valores padrão
    public Ocorrencia() {
        this.dataAbertura = LocalDateTime.now();
        this.status = Status.ABERTO;
    }

    /*
       Método para fechar um chamado
    */
    public void fecharChamado() {
        this.status = Status.FECHADO;
        this.dataFechamento = LocalDateTime.now();
    }

    /**
     * Método para reabrir um chamado
     */
    public void reabrirChamado() {
        this.status = Status.ABERTO;
        this.dataFechamento = null;
    }


    public void setDataFechamento(LocalDateTime dataFechamento) {
        if (dataFechamento != null && dataFechamento.isBefore(this.dataAbertura)) {
            throw new IllegalArgumentException("Data de fechamento inválida");
        }
        this.dataFechamento = dataFechamento;
    }
}