package com.iupi.fintech.mappers;

import com.iupi.fintech.dtos.ProductoFciDto;
import com.iupi.fintech.models.generic.ProductoFCI;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class ProductoFciMapper {

    public abstract ProductoFCI toEntity(ProductoFciDto dto);

    public abstract ProductoFciDto toDto(ProductoFCI entity);

    public abstract void updateEntityFromDto(ProductoFciDto dto, @MappingTarget ProductoFCI entity);

}
