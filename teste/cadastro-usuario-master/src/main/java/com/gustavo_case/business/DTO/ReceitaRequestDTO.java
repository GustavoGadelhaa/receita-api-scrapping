package com.gustavo_case.business.DTO;

public record ReceitaRequestDTO(
        String titulo,
        String descricao,
        String ingredientes,
        String url
) {}
