package com.gustavo_case.infrastructure.repository;

import com.gustavo_case.infrastructure.entitys.ReceitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceitaRepository extends JpaRepository<ReceitaEntity, Integer> {
    List<ReceitaEntity> findReceitaByTituloIgnoreCase(String titulo);
}
