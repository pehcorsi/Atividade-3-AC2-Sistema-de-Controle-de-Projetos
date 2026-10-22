package com.example.controleprojetos.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.controleprojetos.exceptions.ApiErrors;
import com.example.controleprojetos.exceptions.RecursoNaoEncontradoException;
import com.example.controleprojetos.exceptions.RegraNegocioException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handlerRegraNegocioException(RegraNegocioException ex) {
        return new ApiErrors(ex.getErros());
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlerRecursoNaoEncontradoException(RecursoNaoEncontradoException ex) {
        return new ApiErrors(ex.getMessage());
    }

    // JSON malformado ou data em formato inválido no corpo da requisição
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handlerCorpoInvalido(HttpMessageNotReadableException ex) {
        return new ApiErrors("Corpo da requisição inválido. Verifique o JSON e use datas no formato yyyy-MM-dd.");
    }

    // Parâmetro de URL com tipo inválido (ex.: ?inicio=abc ou id não numérico)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handlerTipoInvalido(MethodArgumentTypeMismatchException ex) {
        return new ApiErrors("Valor inválido para o parâmetro '" + ex.getName()
                + "'. Datas devem usar o formato yyyy-MM-dd.");
    }
}
