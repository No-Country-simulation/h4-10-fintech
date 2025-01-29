package com.iupi.fintech.mappers.cuenta;

import com.iupi.fintech.dtos.cuenta.CuentaRequestDto;
import com.iupi.fintech.dtos.cuenta.CuentaResponseDto;
import com.iupi.fintech.models.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CuentaMapper {
    Cuenta toEntity(CuentaRequestDto requestDto);

    CuentaResponseDto toResponse(Cuenta cuenta);

    void updateEntityFromDto(CuentaRequestDto dto, @MappingTarget Cuenta cuenta);
}
