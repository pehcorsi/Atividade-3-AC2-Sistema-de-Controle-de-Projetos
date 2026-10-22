package com.example.controleprojetos.services;

import java.util.List;

import com.example.controleprojetos.dtos.DadosProjetoDTO;
import com.example.controleprojetos.dtos.FuncionarioDTO;

public interface FuncionarioService {

    void adicionar(FuncionarioDTO dto);

    List<DadosProjetoDTO> buscarProjetos(Integer idFuncionario);
}