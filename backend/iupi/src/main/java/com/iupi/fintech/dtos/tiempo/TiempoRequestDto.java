package com.iupi.fintech.dtos.tiempo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record TiempoRequestDto(
        @NotNull(message = "La fecha no puede ser nula.")
        LocalDate fecha,

        @Positive(message = "El año debe ser positivo.")
        int anio,

        @Positive(message = "El mes debe ser positivo.")
        int mes,

        @Positive(message = "El día debe ser positivo.")
        int dia,

        @Positive(message = "El trimestre debe ser positivo.")
        int trimestre,

        @Positive(message = "La semana debe ser positiva.")
        int semana
) {
}
