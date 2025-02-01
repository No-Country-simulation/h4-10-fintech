package com.iupi.fintech.services;

import com.iupi.fintech.dtos.cuenta.CuentaRequestDto;
import com.iupi.fintech.dtos.cuenta.CuentaResponseDto;
import com.iupi.fintech.models.Cuenta;


public interface CuentaService extends GenericService<Cuenta,Long, CuentaRequestDto, CuentaResponseDto> {

    CuentaResponseDto update(Long id, CuentaRequestDto requestDto);

}
