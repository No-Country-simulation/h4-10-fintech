package com.iupi.fintech.repositories;

import com.iupi.fintech.models.generic.ProductoFci;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoFciRepository extends JpaRepository<ProductoFci, Long> {

ProductoFci findBySimbolo(String simbolo);

    List<ProductoFci> findByPerfilInversor(String perfilInversor);

    List<ProductoFci> findByHorizonteInversion(String horizonteInversion);
}
