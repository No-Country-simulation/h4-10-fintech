package com.iupi.fintech.services;

import com.iupi.fintech.dtos.transaccion.TransaccionRequestDto;
import com.iupi.fintech.dtos.transaccion.TransaccionResponseDto;
import com.iupi.fintech.models.Transaccion;

public interface TransaccionService extends GenericService<Transaccion,Long, TransaccionRequestDto, TransaccionResponseDto>{
    TransaccionResponseDto update(Long id, TransaccionRequestDto requestDto);
}
