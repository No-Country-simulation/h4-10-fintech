package com.iupi.fintech.dtos.producto;

import com.iupi.fintech.enums.CategoriaProducto;
import com.iupi.fintech.enums.Moneda;
import com.iupi.fintech.enums.Riesgo;
import com.iupi.fintech.enums.TipoProducto;

public record ProductoResponseDto(
        Long id,
        CategoriaProducto categoria,
        String nombre,
        String descripcion,
        Moneda moneda,
        Double tasaInteres,
        Integer plazoMinimo,
        TipoProducto tipoProducto,
        Double tasaRendimientoEsperada,
        Riesgo riesgo,
        String entidadProveedor
) {
}
