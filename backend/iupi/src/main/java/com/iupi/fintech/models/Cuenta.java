package com.iupi.fintech.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Data
@Table(name = "cuentas")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal monto;

    @Column(nullable = false)
    private BigDecimal invertido;

    @Column(nullable = false)
    private BigDecimal ahorrado;

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true, nullable = false)
    private User user;

    @OneToMany(mappedBy = "cuenta")
    private List<Transaccion> transacciones;
}
