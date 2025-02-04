package com.iupi.fintech.dtos.cuenta;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CuentaRequestDto(
//        @NotBlank(message = "El alias es obligatorio")
//        String alias,
//
//        @NotBlank(message = "El número de cuenta es obligatorio")
//        String numeroCuenta,

        @NotNull(message = "El monto es obligatorio")
        @DecimalMin(value = "0.0", inclusive = true, message = "El monto debe ser mayor a 0")
        BigDecimal invertido,

        @NotNull(message = "El monto es obligatorio")
        @DecimalMin(value = "0.0", inclusive = true, message = "El monto debe ser mayor a 0")
        BigDecimal ahorrado,

        @NotNull(message = "El monto es obligatorio")
        @DecimalMin(value = "0.0", inclusive = true, message = "El monto inicial debe ser mayor o igual a 0")
        BigDecimal monto,

        @NotNull(message = "El ID del usuario es obligatorio")
        Long usuarioId
) {

}
