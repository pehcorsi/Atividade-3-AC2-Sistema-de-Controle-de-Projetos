package com.example.controleprojetos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.controleprojetos.models.Funcionario;
import com.example.controleprojetos.models.Projeto;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    // (c) todos os projetos aos quais o funcionário está vinculado
    @Query("select p from Funcionario f join f.projetos p where f.id = :id")
    List<Projeto> findProjetosByFuncionarioId(@Param("id") Integer id);
}
