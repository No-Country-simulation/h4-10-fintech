package com.iupi.fintech.models;

import com.iupi.fintech.enums.EstadoRegistro;
import com.iupi.fintech.enums.Genero;
import com.iupi.fintech.enums.Role;
import com.iupi.fintech.enums.TipoDeDocumentacion;
import com.iupi.fintech.models.rxFinanciera.RxFinanciera;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;
    private String auth0Id;
    private String nombre;
    private String email;
    private String pais;
    private String direccion;
    private String telefono;
    // documento de identidad
    private String identificacion;
    private LocalDate fechaNacimiento;

    private LocalDate fechaRegistro;
    private LocalDate fechaUltimaConexion;

    // Enums
    @Enumerated(EnumType.STRING)
    private Role rol;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    @Enumerated(EnumType.STRING)
    private EstadoRegistro estadoRegistro;
    @Enumerated(EnumType.STRING)
    private TipoDeDocumentacion tipoIdentificacion;

    // Relaciones

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Cuenta> cuentas;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Perfil perfil;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ObjetivoFinanciero> objetivosFinancieros;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private RxFinanciera rxFinanciera;

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
        perfil.setUser(this); // Sincronizar el perfil con este usuario
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
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
