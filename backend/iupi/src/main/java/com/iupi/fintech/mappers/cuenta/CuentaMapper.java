package com.iupi.fintech.mappers.cuenta;

import com.iupi.fintech.dtos.cuenta.CuentaRequestDto;
import com.iupi.fintech.dtos.cuenta.CuentaResponseDto;
import com.iupi.fintech.models.Cuenta;
import com.iupi.fintech.models.Transaccion;
import com.iupi.fintech.models.User;
import com.iupi.fintech.repositories.TransaccionRepository;
import com.iupi.fintech.repositories.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CuentaMapper {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransaccionRepository transaccionRepository;

    @Mapping(source = "usuarioId", target = "user", qualifiedByName = "longToUser")
    public abstract Cuenta toEntity(CuentaRequestDto requestDto);

    @Mapping(source = "user", target = "usuarioId", qualifiedByName = "userToLong")
    @Mapping(source = "transacciones", target = "transaccionesId", qualifiedByName = "transaccionToLong")
    public abstract CuentaResponseDto toResponse(Cuenta cuenta);

    public abstract void updateEntityFromDto(CuentaRequestDto dto, @MappingTarget Cuenta cuenta);

    @Named("userToLong")
    public Long userToLong(User user) {
        return user != null ? user.getUsuarioId() : null;
    }
    @Named("longToUser")
    public User toUser(Long id) {
        return id != null ? userRepository.findById(id).orElse(null) : null;
    }
    @Named("transaccionToLong")
    public List<Long> transaccionToLong(List<Transaccion> transacciones) {
        if (transacciones == null) {
            return new ArrayList<>();
        }
        return transacciones.stream().map(Transaccion::getTransacciones_id).collect(Collectors.toList());
    }
}
