package com.iupi.fintech.mappers.transaccion;


import com.iupi.fintech.dtos.transaccion.TransaccionRequestDto;
import com.iupi.fintech.dtos.transaccion.TransaccionResponseDto;
import com.iupi.fintech.models.Transaccion;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransaccionMapper {

    TransaccionResponseDto toResponse(Transaccion transaccion);

    Transaccion toEntity(TransaccionRequestDto dto);

    void updateEntityFromDto(TransaccionRequestDto dto, @MappingTarget Transaccion transaccion);
}
