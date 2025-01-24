package com.iupi.fintech.dtos.transaccion;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransaccionResponseDto(
        Long transaccionId,
        Long usuarioId,
        Long perfilId,
        Long productoId,
        String tipoProducto,
        Long tiempoId,
        BigDecimal monto,
        String moneda,
        String tipoTransaccion,
        LocalDateTime fecha
) {
}
