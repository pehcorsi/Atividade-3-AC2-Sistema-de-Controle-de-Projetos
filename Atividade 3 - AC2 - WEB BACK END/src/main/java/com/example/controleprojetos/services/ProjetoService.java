package com.example.controleprojetos.services;

import java.time.LocalDate;
import java.util.List;

import com.example.controleprojetos.dtos.DadosProjetoDTO;
import com.example.controleprojetos.dtos.ProjetoDTO;

public interface ProjetoService {

    void adicionar(ProjetoDTO dto);

    DadosProjetoDTO buscarPorId(Integer idProjeto);

    List<DadosProjetoDTO> listarTodos();

    List<DadosProjetoDTO> buscarPorPeriodo(LocalDate inicio, LocalDate fim);

    void vincularFuncionario(Integer idProjeto, Integer idFuncionario);
}
