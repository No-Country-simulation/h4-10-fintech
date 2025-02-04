package com.iupi.fintech.models;

import com.iupi.fintech.enums.CategoriaProducto;
import com.iupi.fintech.enums.Moneda;
import com.iupi.fintech.enums.Riesgo;
import com.iupi.fintech.enums.TipoProducto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaProducto categoria;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Moneda moneda;

    private Double tasaInteres;

    private Integer plazoMinimo;

    @Enumerated(EnumType.STRING)
    private TipoProducto tipoProducto;

    private Double tasaRendimientoEsperada;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Riesgo riesgo;

    //Mercado
    @Column(nullable = false, length = 100)
    private String entidadProveedor;
}
