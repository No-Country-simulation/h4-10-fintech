package com.iupi.fintech.services.imp;

import com.iupi.fintech.dtos.tiempo.TiempoRequestDto;
import com.iupi.fintech.dtos.tiempo.TiempoResponseDto;
import com.iupi.fintech.exceptions.ApplicationException;
import com.iupi.fintech.mappers.timpo.TiempoMapper;
import com.iupi.fintech.models.Tiempo;
import com.iupi.fintech.repositories.TiempoRepository;
import com.iupi.fintech.services.TiempoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TiempoServiceImpl implements TiempoService {

    private final TiempoRepository tiempoRepository;
    private final TiempoMapper tiempoMapper;


    @Override
    public TiempoResponseDto save(TiempoRequestDto tiempoRequestDto) {
        Tiempo tiempo = tiempoMapper.toEntity(tiempoRequestDto);
        Tiempo tiempoGuardado = tiempoRepository.save(tiempo);
        return tiempoMapper.toResponse(tiempoGuardado);
    }

    @Override
    public Optional<TiempoResponseDto> findById(Long id) {
        return tiempoRepository.findById(id).map(tiempoMapper::toResponse);
    }

    @Override
    public Iterable<TiempoResponseDto> findAll() {
        return tiempoRepository.findAll().stream()
                .map(tiempoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public TiempoResponseDto update(Long id, TiempoRequestDto requestDto) {
        Tiempo tiempoExistente = tiempoRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Tiempo no encontrado con ID: " + id));
        tiempoMapper.updateEntityFromDto(requestDto, tiempoExistente);
        Tiempo tiempoActualizado = tiempoRepository.save(tiempoExistente);
        return tiempoMapper.toResponse(tiempoActualizado);
    }
}
