package com.iupi.fintech.dtos.cuenta;

import com.iupi.fintech.dtos.transaccion.TransaccionResponseDto;

import java.math.BigDecimal;
import java.util.List;

public record CuentaResponseDto(
        Long id,
//        String alias,
//        String numeroCuenta,
        BigDecimal monto,
        Long usuarioId,
        List<Long> transaccionesId
) {

}
