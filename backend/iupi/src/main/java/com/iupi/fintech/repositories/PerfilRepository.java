package com.iupi.fintech.repositories;

import com.iupi.fintech.models.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    Perfil findByUser_UsuarioId(Long id);
}
