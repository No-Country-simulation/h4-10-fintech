package com.iupi.fintech.mappers.producto;

import com.iupi.fintech.dtos.producto.ProductoRequestDto;
import com.iupi.fintech.dtos.producto.ProductoResponseDto;
import com.iupi.fintech.models.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    Producto toEntity(ProductoRequestDto requestDto);

    ProductoResponseDto toResponse(Producto producto);

    void updateEntityFromDto(ProductoRequestDto dto, @MappingTarget Producto producto);
}
