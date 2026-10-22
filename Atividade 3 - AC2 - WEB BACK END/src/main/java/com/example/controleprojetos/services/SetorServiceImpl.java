package com.example.controleprojetos.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.controleprojetos.dtos.DadosSetorDTO;
import com.example.controleprojetos.dtos.SetorDTO;
import com.example.controleprojetos.exceptions.RecursoNaoEncontradoException;
import com.example.controleprojetos.exceptions.RegraNegocioException;
import com.example.controleprojetos.models.Setor;
import com.example.controleprojetos.repositories.SetorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SetorServiceImpl implements SetorService {

    private final SetorRepository setorRepository;

    @Override
    @Transactional
    public void adicionar(SetorDTO dto) {
        if (dto == null || dto.getNome() == null || dto.getNome().isBlank()) {
            throw new RegraNegocioException("O nome do setor é obrigatório.");
        }
        String nome = dto.getNome().trim();
        if (nome.length() > 100) {
            throw new RegraNegocioException("O nome do setor deve ter no máximo 100 caracteres.");
        }
        if (setorRepository.existsByNomeIgnoreCase(nome)) {
            throw new RegraNegocioException("Já existe um setor com o nome '" + nome + "'.");
        }
        setorRepository.save(new Setor(nome));
    }

    @Override
    @Transactional(readOnly = true)
    public DadosSetorDTO buscarPorId(Integer idSetor) {
        return setorRepository.findSetorFetchFuncionarios(idSetor)
                .map(DadosSetorDTO::de)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Setor não encontrado."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DadosSetorDTO> listarTodos() {
        return setorRepository.findAllFetchFuncionarios().stream()
                .map(DadosSetorDTO::de)
                .collect(Collectors.toList());
    }
}
