package com.iupi.fintech.mappers.transaccion;


import com.iupi.fintech.dtos.transaccion.TransaccionRequestDto;
import com.iupi.fintech.dtos.transaccion.TransaccionResponseDto;
import com.iupi.fintech.models.Cuenta;
import com.iupi.fintech.models.Tiempo;
import com.iupi.fintech.models.Transaccion;
import com.iupi.fintech.models.generic.ProductoFci;
import com.iupi.fintech.repositories.CuentaRepository;
import com.iupi.fintech.repositories.ProductoFciRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class TransaccionMapper {

    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private ProductoFciRepository productoFciRepository;


//    @Mapping(source = "cuenta", target = "cuentaId", qualifiedByName = "cuentaToLong")
    @Mapping(source = "tiempo", target = "tiempoId", qualifiedByName = "tiempoToLong")
    @Mapping(source = "productofci", target = "productofci", qualifiedByName = "productoToLong")
    public abstract TransaccionResponseDto toResponse(Transaccion transaccion);

    @Mapping(source = "cuentaId", target = "cuenta", qualifiedByName = "longToCuenta")
    @Mapping(source = "productofci", target = "productofci", qualifiedByName = "longToProducto")
    public abstract Transaccion toEntity(TransaccionRequestDto dto);

    @Mapping(source = "cuentaId", target = "cuenta", qualifiedByName = "longToCuenta")
    @Mapping(source = "productofci", target = "productofci", qualifiedByName = "longToProducto")
    public abstract void updateEntityFromDto(TransaccionRequestDto dto, @MappingTarget Transaccion transaccion);

    @Named("longToCuenta")
    public Cuenta toCuenta(Long id) {
        return id != null ? cuentaRepository.findById(id).orElse(null) : null;
    }

    @Named("cuentaToLong")
    public Long toLong(Cuenta cuenta) {
        return cuenta != null ? cuenta.getCuenta_id() : null;
    }

    @Named("tiempoToLong")
    public Long toLong(Tiempo tiempo) {

    return tiempo != null ? tiempo.getTiempoId() : null;}
    @Named("productoToLong")
    public Long toLong(ProductoFci productoFCI) {
        return productoFCI != null ? productoFCI.getId() : null;
    }
    @Named("longToProducto")
    public ProductoFci toProducto(Long id) {
        return id != null ? productoFciRepository.findById(id).orElse(null) : null;
    }

}
