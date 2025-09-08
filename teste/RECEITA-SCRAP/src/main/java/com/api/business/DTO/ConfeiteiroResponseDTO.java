package com.api.business.DTO;
import java.util.List;

public record ConfeiteiroResponseDTO(
        String nome,
        String nacionalidade,
        String email,
        String dataNascimento,
        List<ReceitaResponseDTO> receitas
) {}
