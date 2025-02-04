package com.iupi.fintech.dtos.transaccion;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransaccionResponseDto(
        Long id,
        Long usuarioId,
        Long productoId,
        String tipoProducto,
        BigDecimal monto,
        String moneda,
        String tipoTransaccion,
        LocalDateTime fecha,
        Long cuentaId,
        Long tiempoId
) {
}
