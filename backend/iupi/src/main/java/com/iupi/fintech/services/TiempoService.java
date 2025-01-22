package com.iupi.fintech.services;

import com.iupi.fintech.dtos.productoFinanciero.ProductoFinancieroRequestDto;
import com.iupi.fintech.dtos.productoFinanciero.ProductoFinancieroResponseDto;
import com.iupi.fintech.dtos.tiempo.TiempoRequestDto;
import com.iupi.fintech.dtos.tiempo.TiempoResponseDto;

import com.iupi.fintech.models.Tiempo;
import org.springframework.stereotype.Service;

@Service
public interface TiempoService extends GenericService<Tiempo, Long, TiempoRequestDto, TiempoResponseDto>{

    TiempoResponseDto update(Long id, TiempoRequestDto requestDto);

}

