package com.example.controleprojetos.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.controleprojetos.dtos.DadosProjetoDTO;
import com.example.controleprojetos.dtos.ProjetoDTO;
import com.example.controleprojetos.exceptions.RecursoNaoEncontradoException;
import com.example.controleprojetos.exceptions.RegraNegocioException;
import com.example.controleprojetos.models.Funcionario;
import com.example.controleprojetos.models.Projeto;
import com.example.controleprojetos.repositories.FuncionarioRepository;
import com.example.controleprojetos.repositories.ProjetoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final FuncionarioRepository funcionarioRepository;

    @Override
    @Transactional
    public void adicionar(ProjetoDTO dto) {
        List<String> erros = new ArrayList<>();

        if (dto == null || dto.getDescricao() == null || dto.getDescricao().isBlank()) {
            erros.add("A descrição do projeto é obrigatória.");
        } else if (dto.getDescricao().trim().length() > 200) {
            erros.add("A descrição do projeto deve ter no máximo 200 caracteres.");
        }
        if (dto == null || dto.getDataInicio() == null) {
            erros.add("A data de início é obrigatória.");
        }
        // dataFim é opcional (projeto em andamento), mas se vier não pode ser anterior ao início
        if (dto != null && dto.getDataInicio() != null && dto.getDataFim() != null
                && dto.getDataFim().isBefore(dto.getDataInicio())) {
            erros.add("A data de fim não pode ser anterior à data de início.");
        }
        if (!erros.isEmpty()) {
            throw new RegraNegocioException(erros);
        }

        projetoRepository.save(new Projeto(dto.getDescricao().trim(), dto.getDataInicio(), dto.getDataFim()));
    }

    @Override
    @Transactional(readOnly = true)
    public DadosProjetoDTO buscarPorId(Integer idProjeto) {
        return projetoRepository.findProjetoFetchFuncionarios(idProjeto)
                .map(DadosProjetoDTO::de)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Projeto não encontrado."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DadosProjetoDTO> listarTodos() {
        return projetoRepository.findAllFetchFuncionarios().stream()
                .map(DadosProjetoDTO::de)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DadosProjetoDTO> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        if (inicio == null || fim == null) {
            throw new RegraNegocioException("Informe as duas datas: inicio e fim.");
        }
        if (fim.isBefore(inicio)) {
            throw new RegraNegocioException("A data final do período não pode ser anterior à data inicial.");
        }
        return projetoRepository.findByDataInicioBetween(inicio, fim).stream()
                .map(DadosProjetoDTO::de)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void vincularFuncionario(Integer idProjeto, Integer idFuncionario) {
        Projeto projeto = projetoRepository.findProjetoFetchFuncionarios(idProjeto)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Projeto não encontrado."));
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionário não encontrado."));

        boolean jaVinculado = projeto.getFuncionarios().stream()
                .anyMatch(f -> f.getId().equals(funcionario.getId()));
        if (jaVinculado) {
            throw new RegraNegocioException("O funcionário já está vinculado a este projeto.");
        }

        projeto.getFuncionarios().add(funcionario);
        projetoRepository.save(projeto);
    }
}
