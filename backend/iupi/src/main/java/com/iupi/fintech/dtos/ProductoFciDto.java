package com.iupi.fintech.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public record ProductoFciDto(

//        Long id,
//        String horizonteInversion,
//        BigDecimal ultimoOperado,
//        Double variacion,
//        String tipoFondo,
//        String invierte,
//        String perfilInversor,
//        Double variacionMensual,
//        Double variacionAnual,
//        String simbolo,
//        String descripcion,
//        String pais,
//        String mercado,
//        String tipo,
//        BigDecimal montoMinimo,
//        List<Long> transaccionesId

        @JsonProperty("id_productosfci") int id,
        @JsonProperty("prob") double probabilidad,
        @JsonProperty("descripcion") String descripcion,
        @JsonProperty("horizonte_inversion") String horizonteInversion,
        @JsonProperty("instrumentos") String instrumentos,
        @JsonProperty("perfil_inversor") String perfilInversor,
        @JsonProperty("tipo_de_fondo") String tipoDeFondo


) {
}
