package com.example.controleprojetos.exceptions;

// Registro inexistente (id inválido) -> HTTP 404
public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(String mensagemErro) {
        super(mensagemErro);
    }
}
