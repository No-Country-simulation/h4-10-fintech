package com.iupi.fintech.dtos.objetivoFinanciero;

public record ObjetivoFinancieroResponseDto(
        Long id,
        String nombreObjetivo,
        Double montoTotal,
        Double montoActual
) {

}
