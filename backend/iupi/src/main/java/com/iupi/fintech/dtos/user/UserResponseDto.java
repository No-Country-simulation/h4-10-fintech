package com.iupi.fintech.dtos.user;

import com.iupi.fintech.enums.EstadoRegistro;
import com.iupi.fintech.enums.Genero;
import com.iupi.fintech.enums.TipoDeDocumentacion;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter@Setter @ToString
public class UserResponseDto {

    private Long usuarioId;
    private String auth0Id;
    private String nombre;
    private String email;
    private String pais;
    private String direccion;
    private String telefono;
    private LocalDate fechaNacimiento;
    private Integer edad;
    private LocalDate fechaRegistro;
    private LocalDate fechaUltimaConexion;

    // Enums

    @Enumerated(EnumType.STRING)
    private Genero genero;
    @Enumerated(EnumType.STRING)
    private EstadoRegistro estadoRegistro;
    @Enumerated(EnumType.STRING)
    private TipoDeDocumentacion tipoIdentificacion;

    private Long perfilId;



}
