package com.wyden.ProjetoWyden.security;

import com.wyden.ProjetoWyden.models.Cadastro;
import com.wyden.ProjetoWyden.repository.CadastroRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CadastroRepository cadastroRepository;

    // 1. Injeta o repositório de usuários
    public CustomUserDetailsService(CadastroRepository cadastroRepository) {
        this.cadastroRepository = cadastroRepository;
    }

    // 2. Método obrigatório que busca o usuário pelo username (no seu caso, email)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 3. Busca o usuário no banco
        Cadastro cadastro = cadastroRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        // 4. Encapsula o usuário em um CustomUserDetails
        return new CustomUserDetails(cadastro);
    }
}