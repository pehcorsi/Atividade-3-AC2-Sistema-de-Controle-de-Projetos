package com.example.controleprojetos.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.example.controleprojetos.models.Projeto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosProjetoDTO {
    private Integer id;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private List<DadosFuncionarioDTO> funcionarios;

    public static DadosProjetoDTO de(Projeto p) {
        return DadosProjetoDTO.builder()
                .id(p.getId())
                .descricao(p.getDescricao())
                .dataInicio(p.getDataInicio())
                .dataFim(p.getDataFim())
                .funcionarios(p.getFuncionarios().stream()
                        .map(DadosFuncionarioDTO::de)
                        .collect(Collectors.toList()))
                .build();
    }
}
