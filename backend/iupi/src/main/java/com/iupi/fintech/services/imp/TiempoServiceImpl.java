package com.iupi.fintech.services.imp;

import com.iupi.fintech.dtos.tiempo.TiempoDto;
import com.iupi.fintech.dtos.tiempo.TiempoRequestDto;
import com.iupi.fintech.exceptions.ApplicationException;
import com.iupi.fintech.mappers.timpo.TiempoMapper;
import com.iupi.fintech.models.Tiempo;
import com.iupi.fintech.repositories.TiempoRepository;
import com.iupi.fintech.services.TiempoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TiempoServiceImpl implements TiempoService {

    private final TiempoRepository tiempoRepository;
    private final TiempoMapper tiempoMapper;



    @Override
    public TiempoDto save(LocalDate fecha) {
            Tiempo tiempo= new Tiempo();
            tiempo.setFecha(fecha);
            tiempo.setAnio(fecha.getYear());
            tiempo.setMes(fecha.getMonthValue());
            tiempo.setDia(fecha.getDayOfMonth());
            tiempo.setSemana(fecha.get(WeekFields.of(Locale.getDefault()).weekOfYear()));
            tiempo.setTrimestre((fecha.getMonthValue() - 1) / 3 + 1);
            Tiempo savedTiempo = tiempoRepository.save(tiempo);
            return tiempoMapper.toResponse(savedTiempo);
        }


    @Override
    public TiempoDto save(TiempoRequestDto tiempoRequestDto) {
        return null;
    }

    @Override
    public Optional<TiempoDto> findById(Long id) {
        return tiempoRepository.findById(id).map(tiempoMapper::toResponse);
    }


    @Override
    public Iterable<TiempoDto> findAll() {
        return tiempoRepository.findAll().stream()
                .map(tiempoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public TiempoDto update(Long id, TiempoRequestDto requestDto) {
        Tiempo tiempoExistente = tiempoRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Tiempo no encontrado con ID: " + id));
        tiempoMapper.updateEntityFromDto(requestDto, tiempoExistente);
        Tiempo tiempoActualizado = tiempoRepository.save(tiempoExistente);
        return tiempoMapper.toResponse(tiempoActualizado);
    }

    @Override
    public TiempoDto findByFecha(LocalDate fecha) {
        return tiempoRepository.findByFecha(fecha).map(tiempoMapper::toResponse).orElse(null);
    }
}
