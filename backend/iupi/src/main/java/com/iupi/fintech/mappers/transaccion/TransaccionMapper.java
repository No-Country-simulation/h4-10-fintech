package com.iupi.fintech.mappers.transaccion;


import com.iupi.fintech.dtos.transaccion.TransaccionRequestDto;
import com.iupi.fintech.dtos.transaccion.TransaccionResponseDto;
import com.iupi.fintech.models.Cuenta;
import com.iupi.fintech.models.Tiempo;
import com.iupi.fintech.models.Transaccion;
import com.iupi.fintech.repositories.CuentaRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class TransaccionMapper {

    @Autowired
    private CuentaRepository cuentaRepository;


//    @Mapping(source = "cuenta", target = "cuentaId", qualifiedByName = "cuentaToLong")
    @Mapping(source = "tiempo", target = "tiempoId", qualifiedByName = "tiempoToLong")
    public abstract TransaccionResponseDto toResponse(Transaccion transaccion);

    // @Mapping(source = "tiempoId", target = "tiempo", qualifiedByName = "longToTiempo")
    public abstract Transaccion toEntity(TransaccionRequestDto dto);

    public abstract void updateEntityFromDto(TransaccionRequestDto dto, @MappingTarget Transaccion transaccion);

    @Named("longToCuenta")
    public Cuenta toCuenta(Long id) {
        return id != null ? cuentaRepository.findById(id).orElse(null) : null;
    }

//    @Named("cuentaToLong")
//    public Long toLong(Cuenta cuenta) {
//        return cuenta != null ? cuenta.getId() : null;
//    }

@Named("tiempoToLong")
public Long toLong(Tiempo tiempo) {
    return tiempo != null ? tiempo.getTiempoId() : null;}

}
