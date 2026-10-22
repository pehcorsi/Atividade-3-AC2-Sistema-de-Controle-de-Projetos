package com.example.controleprojetos.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.controleprojetos.models.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

    // (a) projeto + lista de funcionários vinculados (left join: projeto sem funcionário também retorna)
    @Query("select p from Projeto p left join fetch p.funcionarios where p.id = :id")
    Optional<Projeto> findProjetoFetchFuncionarios(@Param("id") Integer id);

    // (b) projetos cuja data de início está entre inicio e fim (inclusive)
    List<Projeto> findByDataInicioBetween(LocalDate inicio, LocalDate fim);

    // listagem do frontend: todos os projetos já com funcionários
    @Query("select distinct p from Projeto p left join fetch p.funcionarios order by p.id")
    List<Projeto> findAllFetchFuncionarios();
}
