package com.gustavo_case.business.DTO;

public record ConfeiteiroRequestDTO(
        String nome,
        String nacionalidade,
        String email,
        String url,
        String dataNascimento,
        String senha
) {}
