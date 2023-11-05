package br.com.fiap.cp2.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskDto (

        @NotBlank(message = "Title é obrigatório")
        String title,

        @NotBlank(message = "Description é obrigatório")
        String description

) {
}
