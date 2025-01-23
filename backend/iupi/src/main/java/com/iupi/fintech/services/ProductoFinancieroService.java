package com.iupi.fintech.services;

import com.iupi.fintech.dtos.productoFinanciero.ProductoFinancieroRequestDto;
import com.iupi.fintech.dtos.productoFinanciero.ProductoFinancieroResponseDto;
import com.iupi.fintech.models.ProductoFinanciero;

public interface ProductoFinancieroService extends GenericService<ProductoFinanciero,Long, ProductoFinancieroRequestDto, ProductoFinancieroResponseDto> {

    ProductoFinancieroResponseDto update(Long id, ProductoFinancieroRequestDto requestDto);

}
