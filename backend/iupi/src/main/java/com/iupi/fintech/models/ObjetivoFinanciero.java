package com.iupi.fintech.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "objetivos_financieros")
public class ObjetivoFinanciero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreObjetivo;

    @Column(nullable = false)
    private Double montoTotal;

    @Column(nullable = false)
    private Double montoActual;

    @ManyToOne
    private User user;
}
