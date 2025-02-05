package com.iupi.fintech.services.imp;



import com.iupi.fintech.dtos.tiempo.TiempoDto;
import com.iupi.fintech.dtos.transaccion.TransaccionRequestDto;
import com.iupi.fintech.dtos.transaccion.TransaccionResponseDto;
import com.iupi.fintech.exceptions.ApplicationException;
import com.iupi.fintech.mappers.timpo.TiempoMapper;
import com.iupi.fintech.mappers.transaccion.TransaccionMapper;
import com.iupi.fintech.models.Transaccion;
import com.iupi.fintech.repositories.TransaccionRepository;
import com.iupi.fintech.services.TiempoService;
import com.iupi.fintech.services.TransaccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransaccionServiceImpl implements TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final TransaccionMapper transaccionMapper;
    private final TiempoService tiempoService;
    private final TiempoMapper tiempoMapper;


    @Override
    public TransaccionResponseDto save(TransaccionRequestDto transaccionRequestDto) {
        // se debe obtener el usuario autenticado que es el que realiza la transaccion (pendiente)
        LocalDate fecha= LocalDate.now();

        TiempoDto tiempo = tiempoService.findByFecha(fecha);
        if(tiempo == null){
            tiempo = tiempoService.save(fecha);
        }

        Transaccion transaccion = transaccionMapper.toEntity(transaccionRequestDto);
        transaccion.setTiempo(tiempoMapper.toEntity(tiempo));
        transaccion.setFecha(LocalDateTime.now());

       transaccionRepository.save(transaccion);
        return transaccionMapper.toResponse(transaccion);
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
