package com.iupi.fintech.repositories;

import com.iupi.fintech.models.Tiempo;
import com.iupi.fintech.models.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
}
