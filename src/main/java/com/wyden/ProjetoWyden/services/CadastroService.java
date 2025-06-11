package com.wyden.ProjetoWyden.services;

import com.wyden.ProjetoWyden.models.Cadastro;
import com.wyden.ProjetoWyden.repository.CadastroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CadastroService {

    private final CadastroRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Cadastro criar(Cadastro cadastro) {
        if (cadastro.getSenha() == null || cadastro.getSenha().isBlank()) {
            throw new IllegalArgumentException("Senha não pode ser vazia");
        }

        if (repository.existsByEmail(cadastro.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado");
        }

        cadastro.setSenha(passwordEncoder.encode(cadastro.getSenha()));
        return repository.save(cadastro);
    }

    @Transactional
    public void atualizarSenha(Long usuarioId, String novaSenha) {
        if (novaSenha == null || novaSenha.length() < 6) {
            throw new IllegalArgumentException("Senha inválida");
        }

        repository.findById(usuarioId).ifPresent(usuario -> {
            usuario.setSenha(passwordEncoder.encode(novaSenha));
            repository.save(usuario);
        });
    }

    public Optional<Cadastro> buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }
}