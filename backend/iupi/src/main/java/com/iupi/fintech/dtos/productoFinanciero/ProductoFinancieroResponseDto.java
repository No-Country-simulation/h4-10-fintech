package com.iupi.fintech.dtos.productoFinanciero;

public record ProductoFinancieroResponseDto(
        Long id,
        String nombreProducto,
        String descripcion,
        String tipoProducto,
        String moneda,
        Double tasaRendimientoEsperada,
        String riesgo,
        String entidadProveedor
) {
}
