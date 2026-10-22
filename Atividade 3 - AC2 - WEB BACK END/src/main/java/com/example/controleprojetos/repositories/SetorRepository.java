package com.example.controleprojetos.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.controleprojetos.models.Setor;

public interface SetorRepository extends JpaRepository<Setor, Integer> {

    boolean existsByNomeIgnoreCase(String nome);

    // (d) lista todos os setores com seus funcionários (left join: setor sem funcionário também aparece)
    @Query("select distinct s from Setor s left join fetch s.funcionarios")
    List<Setor> findAllFetchFuncionarios();

    // usado por buscarSetorPorId: um setor com seus funcionários
    @Query("select s from Setor s left join fetch s.funcionarios where s.id = :id")
    Optional<Setor> findSetorFetchFuncionarios(@Param("id") Integer id);
}
