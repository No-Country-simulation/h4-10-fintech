package com.iupi.fintech.mappers.timpo;

import com.iupi.fintech.dtos.tiempo.TiempoRequestDto;
import com.iupi.fintech.dtos.tiempo.TiempoResponseDto;
import com.iupi.fintech.models.Tiempo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TiempoMapper {

    TiempoResponseDto toResponse(Tiempo tiempo);

    Tiempo toEntity(TiempoRequestDto dto);

    void updateEntityFromDto(TiempoRequestDto dto, @MappingTarget Tiempo tiempo);

}
