package com.iupi.fintech.dtos.productoFinanciero;

import jakarta.validation.constraints.*;

public record ProductoFinancieroRequestDto(
        @NotBlank(message = "El nombre del producto es obligatorio.")
        @Size(max = 100, message = "El nombre del producto no puede superar los 100 caracteres.")
        String nombreProducto,

        @Size(max = 500, message = "La descripción no puede superar los 500 caracteres.")
        String descripcion,

        @NotNull(message = "El tipo de producto es obligatorio.")
        @Pattern(regexp = "Bonos|ETFs|Acciones|Fondos Comunes|Otros",
                message = "El tipo de producto debe ser uno de los siguientes: Bonos, ETFs, Acciones, Fondos Comunes, Otros.")
        String tipoProducto,

        @Pattern(regexp = "USD|ARS|EUR",
                message = "La moneda debe ser uno de los siguientes valores: USD, ARS, EUR.")
        String moneda,

        @DecimalMin(value = "0.0", message = "La tasa de rendimiento esperada debe ser positiva.")
        @Digits(integer = 3, fraction = 2, message = "La tasa de rendimiento esperada debe tener como máximo 3 enteros y 2 decimales.")
        Double tasaRendimientoEsperada,

        @NotNull(message = "El riesgo es obligatorio.")
        @Pattern(regexp = "Bajo|Medio|Alto",
                message = "El riesgo debe ser uno de los siguientes: Bajo, Medio, Alto.")
        String riesgo,

        @Size(max = 100, message = "El nombre de la entidad no puede superar los 100 caracteres.")
        String entidadProveedor
) {

}
