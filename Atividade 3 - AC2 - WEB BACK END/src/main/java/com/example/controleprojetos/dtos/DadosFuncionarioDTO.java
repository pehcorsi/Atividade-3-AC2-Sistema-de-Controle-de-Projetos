package com.example.controleprojetos.dtos;

import com.example.controleprojetos.models.Funcionario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosFuncionarioDTO {
    private Integer id;
    private String nome;
    private String setor;

    public static DadosFuncionarioDTO de(Funcionario f) {
        return DadosFuncionarioDTO.builder()
                .id(f.getId())
                .nome(f.getNome())
                .setor(f.getSetor() != null ? f.getSetor().getNome() : null)
                .build();
    }
}
