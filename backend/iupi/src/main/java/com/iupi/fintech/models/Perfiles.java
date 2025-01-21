package com.iupi.fintech.models;

import com.iupi.fintech.enums.CapacidadDeAhorro;
import com.iupi.fintech.enums.ConocimientoFinanciero;
import com.iupi.fintech.enums.NivelEconomico;
import com.iupi.fintech.enums.PerfilDeRiesgo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Perfiles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long perfil_id;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Long usuario_id;

    @Enumerated(EnumType.STRING)
    private NivelEconomico nivel_economico;
    @Enumerated(EnumType.STRING)
    private CapacidadDeAhorro capacidad_ahorro;
    @Enumerated(EnumType.STRING)
    private ConocimientoFinanciero conocimiento_financiero;
    @Enumerated(EnumType.STRING)
    private PerfilDeRiesgo perfil_riesgo;
    @Enumerated(EnumType.STRING)
    private String objetivo_principal;




}
