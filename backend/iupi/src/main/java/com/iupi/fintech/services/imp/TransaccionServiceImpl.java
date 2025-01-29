package com.iupi.fintech.services.imp;


import com.iupi.fintech.dtos.tiempo.TiempoRequestDto;
import com.iupi.fintech.dtos.tiempo.TiempoResponseDto;
import com.iupi.fintech.dtos.transaccion.TransaccionRequestDto;
import com.iupi.fintech.dtos.transaccion.TransaccionResponseDto;
import com.iupi.fintech.exceptions.ApplicationException;
import com.iupi.fintech.mappers.transaccion.TransaccionMapper;
import com.iupi.fintech.models.Tiempo;
import com.iupi.fintech.models.Transaccion;
import com.iupi.fintech.repositories.TransaccionRepository;
import com.iupi.fintech.services.TransaccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransaccionServiceImpl implements TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final TransaccionMapper transaccionMapper;


    @Override
    public TransaccionResponseDto save(TransaccionRequestDto transaccionRequestDto) {
        // se debe obtener el usuario autenticado que es el que realiza la transaccion (pendiente)
        Transaccion transaccion = transaccionMapper.toEntity(transaccionRequestDto);
        Transaccion transaccionGuardada = transaccionRepository.save(transaccion);
        return transaccionMapper.toResponse(transaccionGuardada);
    }

    @Override
    public Optional<TransaccionResponseDto> findById(Long id) {
        return transaccionRepository.findById(id).map(transaccionMapper::toResponse);

    }

    @Override
    public Iterable<TransaccionResponseDto> findAll() {
        return transaccionRepository.findAll().stream()
                .map(transaccionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public TransaccionResponseDto update(Long id, TransaccionRequestDto requestDto) {
        Transaccion transaccion = transaccionRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Tiempo no encontrado con ID: " + id));
        transaccionMapper.updateEntityFromDto(requestDto, transaccion);
        Transaccion transaccionActualizado = transaccionRepository.save(transaccion);
        return transaccionMapper.toResponse(transaccionActualizado);
    }
}
