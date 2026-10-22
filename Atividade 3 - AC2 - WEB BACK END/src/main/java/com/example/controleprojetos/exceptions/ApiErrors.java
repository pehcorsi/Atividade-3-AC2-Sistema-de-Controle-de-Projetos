package com.example.controleprojetos.exceptions;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

// Corpo JSON devolvido em erros: { "errors": ["mensagem 1", "mensagem 2"] }
public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(String mensagemErro) {
        this.errors = Arrays.asList(mensagemErro);
    }

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }
}
