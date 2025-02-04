package com.iupi.fintech.repositories;

import com.iupi.fintech.models.Tiempo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface TiempoRepository extends JpaRepository<Tiempo, Long> {
    Optional<Tiempo> findByFecha(LocalDate fecha);
}
