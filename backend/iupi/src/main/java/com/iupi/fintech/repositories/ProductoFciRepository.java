package com.iupi.fintech.repositories;

import com.iupi.fintech.models.generic.ProductoFCI;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ProductoFciRepository extends JpaRepository<ProductoFCI, Long> {

ProductoFCI findBySimbolo(String simbolo);

    List<ProductoFCI> findByPerfilInversor(String perfilInversor);

    List<ProductoFCI> findByHorizonteInversion(String horizonteInversion);
}
