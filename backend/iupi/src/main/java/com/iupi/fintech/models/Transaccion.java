package com.iupi.fintech.models;

import com.iupi.fintech.enums.Moneda;
import com.iupi.fintech.enums.TipoTransaccion;
import com.iupi.fintech.enums.TransaccionTipoProducto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transacciones")
@Data
@NoArgsConstructor
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    Es lo mismo que el Tipo de producto que en productos financieros y productos de ahorros?
    @Enumerated(EnumType.STRING)
    private TransaccionTipoProducto tipoProducto;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    private Moneda moneda;

    @Enumerated(EnumType.STRING)
    private TipoTransaccion tipoTransaccion;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fecha;


    @ManyToOne
    @JoinColumn(name = "tiempo_id",nullable = false)
    private Tiempo tiempo;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", nullable = false)
    private Cuenta cuenta;

    //
//    @ManyToOne
//    @JoinColumn(name = "perfil_id", nullable = false)
//    private Perfil perfil;

//    De donde sale este id, en el script es un id de productos pero existen dos tablas de productos
//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private Producto producto;

}
