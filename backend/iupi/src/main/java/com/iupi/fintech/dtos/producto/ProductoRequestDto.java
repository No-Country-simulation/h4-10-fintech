package com.iupi.fintech.dtos.producto;

import com.iupi.fintech.enums.CategoriaProducto;
import com.iupi.fintech.enums.Moneda;
import com.iupi.fintech.enums.Riesgo;
import com.iupi.fintech.enums.TipoProducto;
import jakarta.validation.constraints.*;

public record ProductoRequestDto(
        @NotNull(message = "La categoría es obligatoria")
        CategoriaProducto categoria,

        @NotBlank(message = "El nombre del producto es obligatorio")
        @Size(max = 100, message = "El nombre del producto no puede exceder los 100 caracteres")
        String nombre,

        String descripcion,

        @NotNull(message = "La moneda es obligatoria")
        Moneda moneda,

        // Validaciones exclusivas de productos de ahorro
        @DecimalMin(value = "0.0", inclusive = false, message = "La tasa de interés debe ser mayor a 0")
        @Digits(integer = 3, fraction = 2, message = "Formato inválido para la tasa de interés")
        Double tasaInteres,

        @Min(value = 1, message = "El plazo mínimo debe ser al menos de 1 día")
        Integer plazoMinimo,

        // Validaciones exclusivas de productos financieros
        TipoProducto tipoProducto,

        @DecimalMin(value = "0.0", inclusive = false, message = "La tasa de rendimiento esperada debe ser mayor a 0")
        @Digits(integer = 3, fraction = 2, message = "Formato inválido para la tasa de rendimiento esperada")
        Double tasaRendimientoEsperada,

        @NotNull(message = "El riesgo es obligatorio")
        Riesgo riesgo,

        @NotBlank(message = "La entidad proveedora es obligatoria")
        @Size(max = 100, message = "El nombre de la entidad proveedora no puede exceder los 100 caracteres")
        String entidadProveedor
) {

}
