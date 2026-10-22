package com.example.controleprojetos.services;

import java.util.List;

import com.example.controleprojetos.dtos.DadosSetorDTO;
import com.example.controleprojetos.dtos.SetorDTO;

public interface SetorService {

    void adicionar(SetorDTO dto);

    DadosSetorDTO buscarPorId(Integer idSetor);

    List<DadosSetorDTO> listarTodos();
}
