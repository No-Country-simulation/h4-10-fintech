package com.iupi.fintech.services.imp;


import com.iupi.fintech.dtos.producto.ProductoRequestDto;
import com.iupi.fintech.dtos.producto.ProductoResponseDto;
import com.iupi.fintech.exceptions.ApplicationException;
import com.iupi.fintech.mappers.producto.ProductoMapper;
import com.iupi.fintech.models.Producto;
import com.iupi.fintech.repositories.ProductoRepository;
import com.iupi.fintech.services.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductoSerrviceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;


    @Override
    public ProductoResponseDto save(ProductoRequestDto productoRequestDto) {
        Producto productoFinanciero = productoMapper.toEntity(productoRequestDto);
        Producto productoGuardado = productoRepository.save(productoFinanciero);
        return productoMapper.toResponse(productoGuardado);
    }

    //Por que retornar un Optional si ya retorna un dto
    @Override
    public Optional<ProductoResponseDto> findById(Long id) {
        return productoRepository.findById(id).map(productoMapper::toResponse);
    }

    //Seria mejor retornar un Page
    @Override
    public Iterable<ProductoResponseDto> findAll() {
        Iterable<Producto> productosFinancieros = productoRepository.findAll();
        return StreamSupport.stream(productosFinancieros.spliterator(), false)
                .map(productoMapper::toResponse)
                .collect(Collectors.toList());
    }

    //¿Falto implementarlo en el metodo en la interfaz generica?
    @Override
    public ProductoResponseDto update(Long id, ProductoRequestDto request) {
        Producto productoFinanciero = productoRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Producto financiero no encontrado con id: " + id));

        productoMapper.updateEntityFromDto(request, productoFinanciero);
        Producto productoGuardado = productoRepository.save(productoFinanciero);

        return productoMapper.toResponse(productoGuardado);
    }

    @Override
    public void deleteById(Long id) {

    }


}
