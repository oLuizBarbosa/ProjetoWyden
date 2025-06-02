package com.wyden.ProjetoWyden.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

public class Login {

    @Getter
    @Setter
    @NotBlank
    @Email
    private String email;

    @Getter
    @Setter
    @NotBlank
    @Size(min = 6)
    @JsonProperty(access = Access.WRITE_ONLY)
    private String senha;

}
