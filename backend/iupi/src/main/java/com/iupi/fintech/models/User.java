package com.iupi.fintech.models;

import com.iupi.fintech.enums.EstadoRegistro;
import com.iupi.fintech.enums.Genero;
import com.iupi.fintech.enums.TipoDeDocumentacion;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "usuario")
public class User implements UserDetails {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;
    private String auth0Id;
    private String nombre;
    private String email;
    private Genero genero;
    private String pais;
    private String direccion;
    private String telefono;
    // Preguntar que seria, Redundancia?
    private String identificacion;

   // private Integer edad;

    private LocalDate fechaRegistro;
    private LocalDate fechaUltimaConexion;

    // Enums
    @Enumerated(EnumType.STRING)
    private LocalDate fechaNacimiento;
    @Enumerated(EnumType.STRING)
    private EstadoRegistro estadoRegistro;
    @Enumerated(EnumType.STRING)
    private TipoDeDocumentacion tipoIdentificacion;

    @OneToOne
    private Perfiles perfiles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
