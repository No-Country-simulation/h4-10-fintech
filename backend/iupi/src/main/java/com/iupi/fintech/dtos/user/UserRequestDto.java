package com.iupi.fintech.dtos.user;

import com.iupi.fintech.enums.Genero;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter
public class UserRequestDto {

    private Long usuarioId;
    private String auth0Id;
    private String nombre;
    private String email;
    private Genero genero;
    private String pais;
    private String direccion;
    private String telefono;
    private LocalDate fechaNacimiento;
}
