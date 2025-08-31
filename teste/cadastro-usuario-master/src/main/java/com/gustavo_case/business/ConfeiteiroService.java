package com.gustavo_case.business;

import com.gustavo_case.infrastructure.entitys.Confeiteiro;
import com.gustavo_case.infrastructure.repository.ConfeiteiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfeiteiroService {

    private final ConfeiteiroRepository confeiteiroRepository;

    public Confeiteiro salvar(Confeiteiro confeiteiro) {
        return confeiteiroRepository.save(confeiteiro);
    }

    public List<Confeiteiro> listarTodos() {
        return confeiteiroRepository.findAll();
    }

    public Confeiteiro buscarPorId(Integer id) {
        return confeiteiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Confeiteiro não encontrado"));
    }
}
