package com.iupi.fintech.mappers.objetivoFinanciero;

import com.iupi.fintech.dtos.objetivoFinanciero.ObjetivoFinancieroRequestDto;
import com.iupi.fintech.dtos.objetivoFinanciero.ObjetivoFinancieroResponseDto;
import com.iupi.fintech.models.ObjetivoFinanciero;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ObjetivoFinancieroMapper {
    ObjetivoFinanciero toEntity(ObjetivoFinancieroRequestDto requestDto);

    ObjetivoFinancieroResponseDto toResponse(ObjetivoFinanciero objetivoFinanciero);

    void updateEntityFromDto(ObjetivoFinancieroRequestDto dto, @MappingTarget ObjetivoFinanciero objetivoFinanciero);
}
