package com.example.controleprojetos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.controleprojetos.dtos.DadosProjetoDTO;
import com.example.controleprojetos.dtos.FuncionarioDTO;
import com.example.controleprojetos.exceptions.RecursoNaoEncontradoException;
import com.example.controleprojetos.exceptions.RegraNegocioException;
import com.example.controleprojetos.models.Funcionario;
import com.example.controleprojetos.models.Setor;
import com.example.controleprojetos.repositories.FuncionarioRepository;
import com.example.controleprojetos.repositories.SetorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final SetorRepository setorRepository;

    @Override
    @Transactional
    public void adicionar(FuncionarioDTO dto) {
        List<String> erros = new ArrayList<>();

        if (dto == null || dto.getNome() == null || dto.getNome().isBlank()) {
            erros.add("O nome do funcionário é obrigatório.");
        } else if (dto.getNome().trim().length() > 150) {
            erros.add("O nome do funcionário deve ter no máximo 150 caracteres.");
        }
        if (dto == null || dto.getSetorId() == null) {
            erros.add("O setor do funcionário é obrigatório.");
        }
        if (!erros.isEmpty()) {
            throw new RegraNegocioException(erros);
        }

        Setor setor = setorRepository.findById(dto.getSetorId())
                .orElseThrow(() -> new RegraNegocioException("Setor não encontrado."));

        Funcionario funcionario = new Funcionario(dto.getNome().trim());
        funcionario.setSetor(setor);
        funcionarioRepository.save(funcionario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DadosProjetoDTO> buscarProjetos(Integer idFuncionario) {
        if (!funcionarioRepository.existsById(idFuncionario)) {
            throw new RecursoNaoEncontradoException("Funcionário não encontrado.");
        }
        return funcionarioRepository.findProjetosByFuncionarioId(idFuncionario).stream()
                .map(DadosProjetoDTO::de)
                .collect(Collectors.toList());
    }
}