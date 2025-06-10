package com.wyden.ProjetoWyden.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter @Setter
public class Cadastro implements UserDetails, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public enum Role {
        ADMIN, USER, SUPPORT;

        public String getAuthority() {
            return "ROLE_" + this.name();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 100)
    @Column(nullable = false)
    private String nome;

    @NotBlank @Email
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role grupo;

    @Size(min = 11, max = 11)
    @Pattern(regexp = "\\d{11}", message = "Telefone deve conter 11 dígitos")
    private String telefone;

    @Column(nullable = false)
    private boolean ativo = true;

    @Column(nullable = false)
    private boolean bloqueado = false;

    // UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(grupo.getAuthority()));
    }

    @Override
    public String getPassword() { return this.senha; }

    @Override
    public String getUsername() { return this.email; }

    @Override
    public boolean isAccountNonExpired() { return this.ativo; }

    @Override
    public boolean isAccountNonLocked() { return this.ativo; }

    @Override
    public boolean isCredentialsNonExpired() { return this.ativo; }

    @Override
    public boolean isEnabled() { return this.ativo; }

    // Métodos auxiliares
    public boolean isAdmin() {
        return this.grupo == Role.ADMIN;
    }
}