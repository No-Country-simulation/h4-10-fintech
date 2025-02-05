package com.iupi.fintech.services;

import com.iupi.fintech.dtos.ProductoFciDto;
import com.iupi.fintech.models.generic.ProductoFCI;

import java.util.List;

public interface ProductoFciService extends GenericServiceDto<ProductoFCI, ProductoFciDto> {


    ProductoFciDto findBySimbolo(String simbolo);

    List<ProductoFciDto> findByParamPerfilInversor(String perfilInversor);

    List<ProductoFciDto> findByHorizonteInversion(String horizonteInversion);

    void updateDataFciNow();
}
