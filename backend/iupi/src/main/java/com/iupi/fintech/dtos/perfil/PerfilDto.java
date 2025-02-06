package com.iupi.fintech.dtos.perfil;

import com.iupi.fintech.enums.*;
import com.iupi.fintech.models.User;
import jakarta.persistence.*;
import lombok.*;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PerfilDto {

    private Long perfilId;

    @Enumerated(EnumType.STRING)
    private NivelEconomico nivelEconomico;
    @Enumerated(EnumType.STRING)
    private CapacidadDeAhorro capacidadAhorro;
    @Enumerated(EnumType.STRING)
    private ConocimientoFinanciero conocimientoFinanciero;
    @Enumerated(EnumType.STRING)
    private PerfilDeRiesgo perfilRiesgo;

    private String objetivoPrincipal;

    private Long userId;

}
