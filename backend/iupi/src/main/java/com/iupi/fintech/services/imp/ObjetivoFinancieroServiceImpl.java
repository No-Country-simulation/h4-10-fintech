package com.iupi.fintech.services.imp;

import com.iupi.fintech.dtos.objetivoFinanciero.ObjetivoFinancieroRequestDto;
import com.iupi.fintech.dtos.objetivoFinanciero.ObjetivoFinancieroResponseDto;
import com.iupi.fintech.mappers.objetivoFinanciero.ObjetivoFinancieroMapper;
import com.iupi.fintech.models.ObjetivoFinanciero;
import com.iupi.fintech.models.Producto;
import com.iupi.fintech.repositories.ObjetivoFinancieroRepository;
import com.iupi.fintech.services.ObjetivoFinancieroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ObjetivoFinancieroServiceImpl implements ObjetivoFinancieroService {

    private final ObjetivoFinancieroRepository objetivoFinancieroRepository;
    private final ObjetivoFinancieroMapper objetivoFinancieroMapper;

    @Override
    public ObjetivoFinancieroResponseDto save(ObjetivoFinancieroRequestDto objetivoFinancieroRequestDto) {
        ObjetivoFinanciero objetivo = objetivoFinancieroMapper.toEntity(objetivoFinancieroRequestDto);
        objetivo = objetivoFinancieroRepository.save(objetivo);
        return objetivoFinancieroMapper.toResponse(objetivo);
    }

    @Override
    public Optional<ObjetivoFinancieroResponseDto> findById(Long id) {
        return objetivoFinancieroRepository.findById(id).map(objetivoFinancieroMapper::toResponse);
    }

    @Override
    public Iterable<ObjetivoFinancieroResponseDto> findAll() {
        Iterable<ObjetivoFinanciero> productosFinancieros = objetivoFinancieroRepository.findAll();
        return StreamSupport.stream(productosFinancieros.spliterator(), false)
                .map(objetivoFinancieroMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public ObjetivoFinancieroResponseDto update(Long id, ObjetivoFinancieroRequestDto requestDto) {
        return null;
    }
}
