package com.iupi.fintech.services;

import com.iupi.fintech.dtos.tiempo.TiempoDto;
import com.iupi.fintech.dtos.tiempo.TiempoRequestDto;

import com.iupi.fintech.models.Tiempo;

import java.time.LocalDate;

public interface TiempoService extends GenericService<Tiempo, Long, TiempoRequestDto, TiempoDto>{

    TiempoDto update(Long id, TiempoRequestDto requestDto);

    TiempoDto findByFecha(LocalDate fecha);

    TiempoDto save(LocalDate fecha);

}

