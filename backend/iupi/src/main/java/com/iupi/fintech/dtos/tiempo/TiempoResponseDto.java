package com.iupi.fintech.dtos.tiempo;

import java.time.LocalDate;

public record TiempoResponseDto(
        Long tiempoId,
        LocalDate fecha,
        int anio,
        int mes,
        int dia,
        int trimestre,
        int semana
) {

}
