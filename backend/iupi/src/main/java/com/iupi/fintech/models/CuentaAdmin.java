package com.iupi.fintech.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Getter
@Setter
@Table(name = "cuenta_admin")
public class CuentaAdmin extends Cuenta{

    @Column(nullable = false)
    private String alias;

    @Column(nullable = false, unique = true)
    private String numeroCuenta;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @Transient
    private BigDecimal invertido;

    @Transient
    private BigDecimal ahorrado;
}
