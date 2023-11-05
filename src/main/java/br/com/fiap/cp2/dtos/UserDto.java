package br.com.fiap.cp2.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserDto (

        @NotBlank(message = "Username é obrigatório")
        String username,

        @NotBlank(message = "Password é obrigatório")
        String password,

        @NotBlank(message = "Role é obrigatório")
        String roles
) {
}
