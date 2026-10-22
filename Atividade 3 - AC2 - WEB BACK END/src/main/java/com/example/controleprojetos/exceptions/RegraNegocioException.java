package com.example.controleprojetos.exceptions;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

// Violação de regra de negócio / validação -> HTTP 400
public class RegraNegocioException extends RuntimeException {

    @Getter
    private final List<String> erros;

    public RegraNegocioException(String mensagemErro) {
        super(mensagemErro);
        this.erros = Arrays.asList(mensagemErro);
    }

    public RegraNegocioException(List<String> erros) {
        super(String.join("; ", erros));
        this.erros = erros;
    }
}
