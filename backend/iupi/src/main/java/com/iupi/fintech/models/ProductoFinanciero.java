package com.iupi.fintech.models;

import com.iupi.fintech.enums.Moneda;
import com.iupi.fintech.enums.Riesgo;
import com.iupi.fintech.enums.TipoProducto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos_financieros")
@Data
@NoArgsConstructor
public class ProductoFinanciero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productoFinancieroId;

    @Column(nullable = false)
    private String nombreProducto;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoProducto tipoProducto;

    @Enumerated(EnumType.STRING)
    private Moneda moneda = Moneda.ARS;

    private Double tasaRendimientoEsperada;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Riesgo riesgo;

    private String entidadProveedor;
}
