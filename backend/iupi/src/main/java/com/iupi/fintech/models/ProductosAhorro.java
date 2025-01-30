package com.iupi.fintech.models;

import com.iupi.fintech.enums.Moneda;
import com.iupi.fintech.enums.Riesgo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productos_ahorro")
public class ProductosAhorro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
