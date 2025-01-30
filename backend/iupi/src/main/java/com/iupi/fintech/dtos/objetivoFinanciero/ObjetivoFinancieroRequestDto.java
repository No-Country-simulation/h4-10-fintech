package com.iupi.fintech.dtos.objetivoFinanciero;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ObjetivoFinancieroRequestDto(
        @NotBlank(message = "El nombre del objetivo es obligatorio") String nombreObjetivo,
        @NotNull(message = "El monto total es obligatorio") @Min(value = 1, message = "El monto total debe ser mayor a 0") Double montoTotal
) {

}
