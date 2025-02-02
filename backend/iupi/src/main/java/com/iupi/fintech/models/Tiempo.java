package com.iupi.fintech.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Tiempo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tiempoId;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private int anio;

    @Column(nullable = false)
    private int mes;

    @Column(nullable = false)
    private int dia;

    @Column(nullable = false)
    private int trimestre;

    @Column(nullable = false)
    private int semana;

    @OneToMany(mappedBy = "tiempo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaccion> transacciones = new ArrayList<>();
}
