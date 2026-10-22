package com.example.controleprojetos.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.example.controleprojetos.models.Setor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosSetorDTO {
    private Integer id;
    private String nome;
    private List<DadosFuncionarioDTO> funcionarios;

    public static DadosSetorDTO de(Setor s) {
        return DadosSetorDTO.builder()
                .id(s.getId())
                .nome(s.getNome())
                .funcionarios(s.getFuncionarios().stream()
                        .map(DadosFuncionarioDTO::de)
                        .collect(Collectors.toList()))
                .build();
    }
}
