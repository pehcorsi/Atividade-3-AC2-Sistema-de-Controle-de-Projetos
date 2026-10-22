package com.example.controleprojetos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.controleprojetos.dtos.DadosSetorDTO;
import com.example.controleprojetos.dtos.SetorDTO;
import com.example.controleprojetos.services.SetorService;

@RestController
@RequestMapping("/api/setores")
public class SetorController {

    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody SetorDTO setor) {
        setorService.adicionar(setor);
    }

    @GetMapping("{idSetor}")
    public DadosSetorDTO buscarSetorPorId(@PathVariable Integer idSetor) {
        return setorService.buscarPorId(idSetor);
    }

    // complemento: usa a query (d) do SetorRepository
    @GetMapping
    public List<DadosSetorDTO> listarSetores() {
        return setorService.listarTodos();
    }
}
