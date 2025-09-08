package com.api.business.Mapper;

import com.api.business.DTO.ReceitaRequestDTO;
import com.api.business.DTO.ReceitaResponseDTO;
import com.api.infrastructure.entitys.ReceitaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ConfeiteiroMapper {


    ReceitaMapper INSTANCE = Mappers.getMapper(ReceitaMapper.class);

    ReceitaResponseDTO paraDTO(ReceitaEntity receita);
    ReceitaEntity paraEntidade(ReceitaRequestDTO dto);



}
