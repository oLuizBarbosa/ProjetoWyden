package com.wyden.ProjetoWyden.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "usuarios")
public class Cadastro implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    public enum Role {
        ADMIN, USER, SUPPORT;

        public String getPrefixedRole() {
            return "ROLE_" + this.name();
        }
    }

    // Getters e Setters
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String nome;

    @Setter
    @Getter
    @NotBlank
    @Email
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Setter
    @Getter
    @NotBlank
    @Size(min = 6, max = 100)
    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(nullable = false)
    private String senha;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role grupo;

    @Setter
    @Getter
    @Size(min = 11, max = 11)
    @Column(length = 11)
    private String telefone;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(grupo.getPrefixedRole()));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email; // Usamos email como username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Conta nunca expira
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Conta nunca é bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Credenciais nunca expiram
    }

    @Override
    public boolean isEnabled() {
        return true; // Conta sempre ativa
    }

    public @NotNull Role getGrupo() {
        return grupo;
    }

    public void setGrupo(@NotNull Role grupo) {
        this.grupo = grupo;
    }

}