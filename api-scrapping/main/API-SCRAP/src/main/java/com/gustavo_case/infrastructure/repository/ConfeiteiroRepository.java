package com.gustavo_case.infrastructure.repository;

import com.gustavo_case.infrastructure.entitys.Confeiteiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfeiteiroRepository extends JpaRepository<Confeiteiro, Integer> {
}
