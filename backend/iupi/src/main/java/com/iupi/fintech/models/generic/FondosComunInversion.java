package com.iupi.fintech.models.generic;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class FondosComunInversion {

    private String horizonteInversion;
    private BigDecimal ultimoOperado;
    private Double variacion;
    private String tipoFondo;
    private String invierte;
    private String perfilInversor;
    private Double variacionMensual;
    private Double variacionAnual;
    private String simbolo;
    private String descripcion;
    private String pais;
    private String mercado;
    private String tipo;
}
