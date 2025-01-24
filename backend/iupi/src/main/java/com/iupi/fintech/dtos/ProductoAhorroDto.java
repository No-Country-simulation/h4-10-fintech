package com.iupi.fintech.dtos;

import com.iupi.fintech.enums.Moneda;
import com.iupi.fintech.enums.Riesgo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoAhorroDto {

    private Long productoAhorroid;
    private String nombreProducto;
    private String descripcion;
    private Double tasaDeInteres;

    private Integer plazoMinimo;

    private String entidadProveedor;

    @Enumerated(EnumType.STRING)
    private Moneda moneda;
    @Enumerated(EnumType.STRING)
    private Riesgo riesgo;

}
