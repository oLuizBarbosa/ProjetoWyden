package com.wyden.ProjetoWyden.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.wyden.ProjetoWyden.models.Cadastro;
import com.wyden.ProjetoWyden.repository.CadastroRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Logger
    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final CadastroRepository cadastroRepository;

    // Injeção de dependência via construtor
    public CustomUserDetailsService(CadastroRepository cadastroRepository) {
        this.cadastroRepository = cadastroRepository;
    }

    // Metodo obrigatório que busca o usuário pelo username (no seu caso, email)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Log de tentativa de login (nível INFO)
        log.info("🔑 Tentativa de login com email: {}", email);

        try {
            Cadastro cadastro = cadastroRepository.findByEmail(email)
                    .orElseThrow(() -> {
                        // Log de erro (nível ERROR)
                        log.error("❌ Usuário não encontrado para o email: {}", email);
                        return new UsernameNotFoundException("Credenciais inválidas");
                    });

            // Log de sucesso (nível DEBUG)
            log.debug("✅ Usuário encontrado: {} | Grupo: {}", cadastro.getEmail(), cadastro.getGrupo());

            return new CustomUserDetails(cadastro);

        } catch (Exception ex) {
            // Log de erro inesperado
            log.error("⚠️ Erro durante autenticação para o email: {}", email, ex);
            throw ex;
        }
    }


}



















/*private final CadastroRepository cadastroRepository;

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
    */
