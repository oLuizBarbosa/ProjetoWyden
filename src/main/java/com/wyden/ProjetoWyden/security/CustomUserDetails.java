package com.wyden.ProjetoWyden.security;

import com.wyden.ProjetoWyden.models.Cadastro;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final Cadastro cadastro; // Sua entidade

    public CustomUserDetails(Cadastro cadastro) {
        this.cadastro = cadastro;
    }

    // Mapeia as roles (grupos) para o formato que o Spring Security entende
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + cadastro.getGrupo().name())
        );
    }

    // ---- Métodos obrigatórios (delegate para a entidade Cadastro) ----
    @Override
    public String getPassword() {
        return cadastro.getSenha(); // Já criptografada
    }

    @Override
    public String getUsername() {
        return cadastro.getEmail(); // Usamos email como username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Pode implementar lógica se necessário
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Pode implementar lógica se necessário
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Pode implementar lógica se necessário
    }

    @Override
    public boolean isEnabled() {
        return true; // Pode implementar lógica se necessário
    }
}