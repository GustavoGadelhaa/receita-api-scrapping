package com.gustavo_case.business.Mapper;

import com.gustavo_case.business.DTO.ConfeiteiroRequestDTO;
import com.gustavo_case.business.DTO.ConfeiteiroResponseDTO;
import com.gustavo_case.business.DTO.ReceitaRequestDTO;
import com.gustavo_case.business.DTO.ReceitaResponseDTO;
import com.gustavo_case.infrastructure.entitys.ConfeiteiroEntity;
import com.gustavo_case.infrastructure.entitys.ReceitaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ConfeiteiroMapper {


    ReceitaMapper INSTANCE = Mappers.getMapper(ReceitaMapper.class);

    ReceitaResponseDTO paraDTO(ReceitaEntity receita);
    ReceitaEntity paraEntidade(ReceitaRequestDTO dto);



}
