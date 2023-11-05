package br.com.fiap.cp2.controllers;

import br.com.fiap.cp2.dtos.ErroDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class GenericController {

    List<String> getErrors(@NotNull BindingResult result) {
        return result.getAllErrors().stream().map(error -> {
            FieldError fieldError = (FieldError) error;
            return fieldError.getField().toUpperCase(Locale.ROOT) + " : " + error.getDefaultMessage();

        }).collect(Collectors.toList());
    }

    ResponseEntity<Object> handleErrors(@NotNull Exception e) {
        ErroDto erroDTO = new ErroDto("Ocorreu um erro: ", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroDTO);
    }
}
