package com.iupi.fintech.services;

import com.iupi.fintech.dtos.producto.ProductoRequestDto;
import com.iupi.fintech.dtos.producto.ProductoResponseDto;
import com.iupi.fintech.models.Producto;


public interface ProductoService extends GenericService<Producto,Long, ProductoRequestDto, ProductoResponseDto> {

    ProductoResponseDto update(Long id, ProductoRequestDto requestDto);

}
