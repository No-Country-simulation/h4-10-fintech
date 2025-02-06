package com.iupi.fintech.models.generic;


import com.iupi.fintech.models.Transaccion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "productosFCI")
public class ProductoFci {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // tipo de inversion, corto,largo o mediano plazo
    private String horizonteInversion;
    // precio actual
    private BigDecimal ultimoOperado;
    // porcentaje de variacion del fondo
    private Double variacion;
    // categoria del fondo (renta_fija_dolares, renta_fija_pesos, renta_variable_dolares, renta_variable_pesos)
    private String tipoFondo;
    // en que invierte el bono, descripcion
    private String invierte;
    // agresivo o conservador
    private String perfilInversor;
    private Double variacionMensual;
    private Double variacionAnual;
    // identificador del fondo
    private String simbolo;
    // nombre del fondo
    private String descripcion;
    private String pais;
    // si es en argentina u otro pais
    private String mercado;
    //  fondo comun invercion// cedear/ bonos etc
    private String tipo;
    // monto minimo para operar
    private BigDecimal montoMinimo;

    @OneToMany(mappedBy = "productofci", fetch = FetchType.LAZY)
    private List<Transaccion> transacciones= new ArrayList<>();
}
