package com.iupi.fintech.services;

import com.iupi.fintech.dtos.objetivoFinanciero.ObjetivoFinancieroRequestDto;
import com.iupi.fintech.dtos.objetivoFinanciero.ObjetivoFinancieroResponseDto;
import com.iupi.fintech.models.ObjetivoFinanciero;

public interface ObjetivoFinancieroService extends GenericService<ObjetivoFinanciero,Long, ObjetivoFinancieroRequestDto, ObjetivoFinancieroResponseDto> {
    ObjetivoFinancieroResponseDto update(Long id, ObjetivoFinancieroRequestDto requestDto);
}
