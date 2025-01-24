package com.iupi.fintech.services.imp;

import com.iupi.fintech.dtos.productoFinanciero.ProductoFinancieroRequestDto;
import com.iupi.fintech.dtos.productoFinanciero.ProductoFinancieroResponseDto;
import com.iupi.fintech.exceptions.ApplicationException;
import com.iupi.fintech.mappers.productoFinanciero.ProductoFinancieroMapper;
import com.iupi.fintech.models.ProductoFinanciero;
import com.iupi.fintech.repositories.ProductoFinancieroRepository;
import com.iupi.fintech.services.ProductoFinancieroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductoFinancieroSerrviceImpl implements ProductoFinancieroService {

    private final ProductoFinancieroRepository productoFinancieroRepository;
    private final ProductoFinancieroMapper productoFinancieroMapper;


    @Override
    public ProductoFinancieroResponseDto save(ProductoFinancieroRequestDto productoFinancieroRequestDto) {
        ProductoFinanciero productoFinanciero = productoFinancieroMapper.toEntity(productoFinancieroRequestDto);
        ProductoFinanciero productoGuardado = productoFinancieroRepository.save(productoFinanciero);
        return productoFinancieroMapper.toResponse(productoGuardado);
    }

    //Por que retornar un Optional si ya retorna un dto
    @Override
    public Optional<ProductoFinancieroResponseDto> findById(Long id) {
        return productoFinancieroRepository.findById(id).map(productoFinancieroMapper::toResponse);
    }

    //Seria mejor retornar un Page
    @Override
    public Iterable<ProductoFinancieroResponseDto> findAll() {
        Iterable<ProductoFinanciero> productosFinancieros = productoFinancieroRepository.findAll();
        return StreamSupport.stream(productosFinancieros.spliterator(), false)
                .map(productoFinancieroMapper::toResponse)
                .collect(Collectors.toList());
    }

    //¿Falto implementarlo en el metodo en la interfaz generica?
    @Override
    public ProductoFinancieroResponseDto update(Long id, ProductoFinancieroRequestDto request) {
        ProductoFinanciero productoFinanciero = productoFinancieroRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Producto financiero no encontrado con id: " + id));

        productoFinancieroMapper.updateEntityFromDto(request, productoFinanciero);
        ProductoFinanciero productoGuardado = productoFinancieroRepository.save(productoFinanciero);

        return productoFinancieroMapper.toResponse(productoGuardado);
    }

    @Override
    public void deleteById(Long id) {

    }


}
