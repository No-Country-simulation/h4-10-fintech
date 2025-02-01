package com.iupi.fintech.dtos.rxFinanciera;

import com.iupi.fintech.enums.Riesgo;
import com.iupi.fintech.models.rxFinanciera.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class RxFinancieraDto {

    private Long id;
    private Integer edad;
    private Boolean personasACargo;
    private Integer cantPersonasACargo;
    @Enumerated(EnumType.STRING)
    private TipoDeTrabajo tipoDeTrabajo;
    private Double ingresoMensual;
    @Enumerated(EnumType.STRING)
    private TipoDeIngreso tipoIngreso;
    private Boolean otrosIngresos;
    @Enumerated(EnumType.STRING)
    private VariacionIngreso variacionIngreso;
    private Double importeGastosBasicos;
    private Double importeGastosFijos;
    private Double importeGastosRecreativos;
    private Double importeGastosEducativos;
    private Double ahorroActual;
    private Double porcentajeAhorro;
    private Boolean usoHerramientasAhorro;
    @Enumerated(EnumType.STRING)
    private Deudas deudas;
    private Double porcentajeDeudas;
    private Boolean deudasOtraMoneda;
    @Enumerated(EnumType.STRING)
    private InversionActual inversionActual;
    private Boolean interesEnAprender;
    @Enumerated(EnumType.STRING)
    private Riesgo riesgo;
    @Enumerated(EnumType.STRING)
    private Objetivo objetivo;

    private Boolean recibirRecomendaciones;

    private Long userId;

}
