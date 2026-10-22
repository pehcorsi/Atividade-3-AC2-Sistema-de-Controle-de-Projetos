package com.example.controleprojetos.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.controleprojetos.dtos.DadosProjetoDTO;
import com.example.controleprojetos.dtos.ProjetoDTO;
import com.example.controleprojetos.services.ProjetoService;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody ProjetoDTO projeto) {
        projetoService.adicionar(projeto);
    }

    @GetMapping("{id}")
    public DadosProjetoDTO buscarProjetoPorId(@PathVariable Integer id) {
        return projetoService.buscarPorId(id);
    }

    @PutMapping("{idProjeto}/funcionarios/{idFuncionario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vincularFuncionario(@PathVariable Integer idProjeto, @PathVariable Integer idFuncionario) {
        projetoService.vincularFuncionario(idProjeto, idFuncionario);
    }

    // complemento (necessário ao frontend): lista todos os projetos.
    // Com ?inicio=yyyy-MM-dd&fim=yyyy-MM-dd usa a query (b) e filtra pela data de início.
    @GetMapping
    public List<DadosProjetoDTO> listarProjetos(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        if (inicio == null && fim == null) {
            return projetoService.listarTodos();
        }
        return projetoService.buscarPorPeriodo(inicio, fim);
    }
}
