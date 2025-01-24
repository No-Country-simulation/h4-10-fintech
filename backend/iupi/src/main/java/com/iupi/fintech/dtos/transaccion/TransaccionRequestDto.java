package com.iupi.fintech.dtos.transaccion;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransaccionRequestDto(

//        @NotNull(message = "El ID del usuario es obligatorio.")
//        Long usuarioId,

        @NotNull(message = "El ID del perfil es obligatorio.")
        Long perfilId,

//        @NotNull(message = "El ID del producto es obligatorio.")
//        Long productoId,

        @NotNull(message = "El tipo de producto es obligatorio.")
        @Pattern(regexp = "Ahorro|Financiero", message = "El tipo de producto debe ser 'Ahorro' o 'Financiero'.")
        String tipoProducto,

        //definir si el tiempo se crea con la transaccion o ya exissten tiempos creados, no entiendo esa tabla
        @NotNull(message = "El ID del tiempo es obligatorio.")
        Long tiempoId,

        @NotNull(message = "El monto es obligatorio.")
        @Positive(message = "El monto debe ser un valor positivo.")
        BigDecimal monto,

        @Pattern(regexp = "USD|ARS|EUR", message = "La moneda debe ser 'USD', 'ARS' o 'EUR'.")
        String moneda,

        @NotNull(message = "El tipo de transacción es obligatorio.")
        @Pattern(regexp = "Deposito|Retiro|Inversion", message = "El tipo de transacción debe ser 'Deposito', 'Retiro' o 'Inversion'.")
        String tipoTransaccion
) {}

