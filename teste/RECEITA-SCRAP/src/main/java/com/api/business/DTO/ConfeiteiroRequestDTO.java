package com.api.business.DTO;

public record ConfeiteiroRequestDTO(
        String nome,
        String nacionalidade,
        String email,
        String url,
        String dataNascimento,
        String senha
) {}
