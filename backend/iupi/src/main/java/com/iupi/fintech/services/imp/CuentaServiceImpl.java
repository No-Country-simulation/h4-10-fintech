package com.iupi.fintech.services.imp;

import com.iupi.fintech.dtos.cuenta.CuentaRequestDto;
import com.iupi.fintech.dtos.cuenta.CuentaResponseDto;
import com.iupi.fintech.mappers.cuenta.CuentaMapper;
import com.iupi.fintech.models.Cuenta;
import com.iupi.fintech.models.User;
import com.iupi.fintech.repositories.CuentaRepository;
import com.iupi.fintech.repositories.UserRepository;
import com.iupi.fintech.services.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final CuentaMapper cuentaMapper;
    private final UserRepository userRepository;

    @Override
    public CuentaResponseDto save(CuentaRequestDto cuentaRequestDto) {
        //¿La cuenta deberia crearse al momeento de crear el usuario?
        User user = userRepository.findById(cuentaRequestDto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Cuenta cuenta = cuentaMapper.toEntity(cuentaRequestDto);
        cuenta.setUser(user);

        cuenta = cuentaRepository.save(cuenta);
        return cuentaMapper.toResponse(cuenta);
    }

    @Override
    public Optional<CuentaResponseDto> findById(Long id) {
        return cuentaRepository.findById(id).map(cuentaMapper::toResponse);
    }

    @Override
    public Iterable<CuentaResponseDto> findAll() {
        Iterable<Cuenta> productosFinancieros = cuentaRepository.findAll();
        return StreamSupport.stream(productosFinancieros.spliterator(), false)
                .map(cuentaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CuentaResponseDto update(Long id, CuentaRequestDto requestDto) {
        //¿la cuenta solo deberia ser editada por el usuario propietario?
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        // Actualizar los valores permitidos
        cuentaMapper.updateEntityFromDto(requestDto, cuenta);

        // Guardar en la BD
        cuenta = cuentaRepository.save(cuenta);

        // Convertir y retornar el DTO actualizado
        return cuentaMapper.toResponse(cuenta);
    }

    @Override
    public void deleteById(Long aLong) {

    }


}
