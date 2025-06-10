package com.wyden.ProjetoWyden.security;

import com.wyden.ProjetoWyden.models.Cadastro;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static Cadastro getUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
            return null;
        }

        return ((CustomUserDetails) authentication.getPrincipal()).getUsuario();
    }
}