package com.wyden.ProjetoWyden.security;

import com.wyden.ProjetoWyden.repository.CadastroRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final CadastroRepository cadastroRepository;

    public CustomUserDetailsService(CadastroRepository cadastroRepository) {
        this.cadastroRepository = cadastroRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return cadastroRepository.findByEmail(email)
                .map(usuario -> {
                    log.info("Usuário autenticado: {}", email);
                    return new CustomUserDetails(usuario);
                })
                .orElseThrow(() -> {
                    log.warn("Tentativa de login com email inválido: {}", email);
                    throw new UsernameNotFoundException("Usuário não encontrado");
                });
    }
}