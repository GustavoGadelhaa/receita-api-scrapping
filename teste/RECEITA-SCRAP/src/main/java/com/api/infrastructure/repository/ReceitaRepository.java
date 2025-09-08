package com.api.infrastructure.repository;

import com.api.infrastructure.entitys.ReceitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceitaRepository extends JpaRepository<ReceitaEntity, Integer> {
    List<ReceitaEntity> findReceitaByTituloIgnoreCase(String titulo);
}
