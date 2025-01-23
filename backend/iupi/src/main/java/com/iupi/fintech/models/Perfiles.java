package com.iupi.fintech.models;

import com.iupi.fintech.enums.CapacidadDeAhorro;
import com.iupi.fintech.enums.ConocimientoFinanciero;
import com.iupi.fintech.enums.NivelEconomico;
import com.iupi.fintech.enums.PerfilDeRiesgo;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "perfiles")
public class Perfiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "perfil")
    private List<Transaccion> transacciones;




}
