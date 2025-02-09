package com.iupi.fintech.mappers;

import com.iupi.fintech.dtos.ProductoFciDto;
import com.iupi.fintech.models.Transaccion;
import com.iupi.fintech.models.generic.ProductoFci;
import com.iupi.fintech.repositories.TransaccionRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProductoFciMapper {
    @Autowired
    private TransaccionRepository transaccionRepository;


    @Mapping(source = "transaccionesId", target = "transacciones",qualifiedByName = "longToTransaccion")
    public abstract ProductoFci toEntity(ProductoFciDto dto);

    @Mapping(source = "transacciones", target = "transaccionesId",qualifiedByName = "transaccionToLong")
    public abstract ProductoFciDto toDto(ProductoFci entity);

    public abstract void updateEntityFromDto(ProductoFciDto dto, @MappingTarget ProductoFci entity);

    @Named("longToTransaccion")
    public List<Transaccion> longToTransaccion(List<Long> transaccionesId) {
        return transaccionesId.stream().map(id -> transaccionRepository.findById(id).orElse(null)).toList();

    }
    @Named("transaccionToLong")
    public List<Long> transaccionToLong(List<Transaccion> transacciones) {
        return transacciones.stream().map(Transaccion::getTransacciones_id).toList();
    }

}
