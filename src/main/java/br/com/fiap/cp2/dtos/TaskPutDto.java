package br.com.fiap.cp2.dtos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record TaskPutDto (

        @NotBlank(message = "Title é obrigatório")
        String title,

        @NotBlank(message = "Description é obrigatório")
        String description,

        @NotBlank(message = "Status é obrigatório")
        String status

) {
}
