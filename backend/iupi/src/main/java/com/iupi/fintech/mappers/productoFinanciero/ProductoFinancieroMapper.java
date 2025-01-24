package com.iupi.fintech.mappers.productoFinanciero;

import com.iupi.fintech.dtos.productoFinanciero.ProductoFinancieroRequestDto;
import com.iupi.fintech.dtos.productoFinanciero.ProductoFinancieroResponseDto;
import com.iupi.fintech.models.ProductoFinanciero;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductoFinancieroMapper {

    ProductoFinanciero toEntity(ProductoFinancieroRequestDto requestDto);

    ProductoFinancieroResponseDto toResponse(ProductoFinanciero productoFinanciero);

    void updateEntityFromDto(ProductoFinancieroRequestDto dto, @MappingTarget ProductoFinanciero productoFinanciero);
}
