package com.iupi.fintech.dtos.tiempo;

import java.time.LocalDate;
import java.util.List;

public record TiempoDto(
        Long tiempoId,
        LocalDate fecha,
        int anio,
        int mes,
        int dia,
        int trimestre,
        int semana,
        List<Long> transaccionesId
) {

}
