package com.iupi.fintech.repositories;

import com.iupi.fintech.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
