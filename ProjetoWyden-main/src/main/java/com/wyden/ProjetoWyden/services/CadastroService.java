package com.wyden.ProjetoWyden.services;

import com.wyden.ProjetoWyden.models.Cadastro;
import com.wyden.ProjetoWyden.repository.CadastroRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CadastroService {

    private final CadastroRepository repository;
    private final PasswordEncoder passwordEncoder;

    public CadastroService(CadastroRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Cadastro criar(Cadastro cadastro) {
        cadastro.setSenha(passwordEncoder.encode(cadastro.getSenha()));
        return repository.save(cadastro);
    }

    public List<Cadastro> buscarTodos() {
        return repository.findAll();
    }

    public Cadastro buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // Consulta customizada para relatório de usuários por grupo
    public List<Object[]> relatorioUsuariosPorGrupo() {
        return repository.countUsersByGroup();
    }
}