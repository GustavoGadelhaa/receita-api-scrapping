package com.gustavo_case.infrastructure.repository;

import com.gustavo_case.infrastructure.entitys.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Integer> {
    List<Receita> findReceitaByTituloIgnoreCase(String titulo);
}
