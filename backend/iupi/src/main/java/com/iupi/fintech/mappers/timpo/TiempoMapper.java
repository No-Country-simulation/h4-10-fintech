package com.iupi.fintech.mappers.timpo;

import com.iupi.fintech.dtos.tiempo.TiempoRequestDto;
import com.iupi.fintech.dtos.tiempo.TiempoDto;
import com.iupi.fintech.models.Tiempo;
import com.iupi.fintech.models.Transaccion;
import com.iupi.fintech.repositories.TransaccionRepository;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class TiempoMapper {

    TransaccionRepository transaccionRepository;

    @Mapping(source = "transacciones", target = "transaccionesId",qualifiedByName = "transaccionToLong")
    @Mapping(source = "tiempoId", target = "tiempoId")
    public abstract TiempoDto toResponse(Tiempo tiempo);

    @Mapping(source = "transaccionesId", target = "transacciones",qualifiedByName = "longToTransaccion")
    public abstract Tiempo toEntity(TiempoDto dto);

    public abstract void updateEntityFromDto(TiempoRequestDto dto, @MappingTarget Tiempo tiempo);

    @Named("transaccionToLong")
    public List<Long> transaccionToLong(List<Transaccion> transacciones) {
        return transacciones.stream().map(Transaccion::getId).collect(Collectors.toList());
    }

    @Named("longToTransaccion")
    public List<Transaccion> longToTransaccion(List<Long> transaccionesId) {
        return transaccionesId.stream().map(id -> transaccionRepository.findById(id).orElse(null)).collect(Collectors.toList());
    }
}
