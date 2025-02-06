package com.iupi.fintech.models;

import com.iupi.fintech.enums.EstadoRegistro;
import com.iupi.fintech.enums.Genero;
import com.iupi.fintech.enums.TipoDeDocumentacion;
import com.iupi.fintech.models.rxFinanciera.RxFinanciera;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter @ToString
@Table(name = "admin")
public class Admin extends User {

   @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
private List<CuentaAdmin> cuentasAdmin;

 @Transient
    private EstadoRegistro estadoRegistro;

    @Transient
    private Perfil perfil;

    @Transient
    private List<ObjetivoFinanciero> objetivosFinancieros;

    @Transient
    private RxFinanciera rxFinanciera;




}
