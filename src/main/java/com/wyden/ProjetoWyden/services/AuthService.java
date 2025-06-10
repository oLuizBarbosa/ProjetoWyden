package com.wyden.ProjetoWyden.services;

import com.wyden.ProjetoWyden.models.Cadastro;
import com.wyden.ProjetoWyden.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    public Cadastro getUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
            throw new IllegalStateException("Nenhum usuário autenticado");
        }

        return ((CustomUserDetails) authentication.getPrincipal()).getUsuario();
    }

    public boolean temPermissao(Long usuarioId) {
        Cadastro usuario = getUsuarioLogado();
        return usuario.getId().equals(usuarioId) || usuario.isAdmin();
    }
}