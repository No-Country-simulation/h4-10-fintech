package com.iupi.fintech.dtos;

import java.math.BigDecimal;
import java.util.List;

public record ProductoFciDto(

        Long id,
        String horizonteInversion,
        BigDecimal ultimoOperado,
        Double variacion,
        String tipoFondo,
        String invierte,
        String perfilInversor,
        Double variacionMensual,
        Double variacionAnual,
        String simbolo,
        String descripcion,
        String pais,
        String mercado,
        String tipo,
        BigDecimal montoMinimo,
        List<Long> transaccionesId


) {
}
