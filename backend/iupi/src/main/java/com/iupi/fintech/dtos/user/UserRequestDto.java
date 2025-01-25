package com.iupi.fintech.dtos.user;

import com.iupi.fintech.enums.Genero;
import com.iupi.fintech.enums.TipoDeDocumentacion;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter@Setter @ToString
public class UserRequestDto {

    private Long usuarioId;
    private String auth0Id;
    private String nombre;
    private String email;
    private String pais;
    private String direccion;
    private String telefono;
    private String identificacion;
    private LocalDate fechaNacimiento;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    @Enumerated(EnumType.STRING)
    private TipoDeDocumentacion tipoIdentificacion;

}
