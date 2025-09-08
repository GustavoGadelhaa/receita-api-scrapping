package com.api.business.DTO;

public record ReceitaRequestDTO(
        String titulo,
        String descricao,
        String ingredientes,
        String url
) {}
