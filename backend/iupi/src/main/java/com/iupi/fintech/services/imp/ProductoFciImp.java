package com.iupi.fintech.services.imp;

import com.iupi.fintech.dtos.ProductoFciDto;
import com.iupi.fintech.enums.PerfilDeRiesgo;
import com.iupi.fintech.exceptions.ApplicationException;
import com.iupi.fintech.mappers.ProductoFciMapper;
import com.iupi.fintech.repositories.ProductoFciRepository;
import com.iupi.fintech.services.ProductoFciService;
import com.iupi.fintech.utils.iol.FciUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoFciImp implements ProductoFciService{

    private final ProductoFciMapper productoFciMapper;
    private final ProductoFciRepository productoRepository;
    private final FciUpdateService fciUpdateService;

    @Autowired
    public ProductoFciImp( ProductoFciMapper productoFciMapper, ProductoFciRepository productoFciRepository, FciUpdateService fciUpdateService) {
        this.fciUpdateService = fciUpdateService;
        this.productoFciMapper = productoFciMapper;
        this.productoRepository = productoFciRepository;
    }

    @Override
    public ProductoFciDto save(ProductoFciDto productoFciDto) {
       return null;
    }

    @Override
    public Optional<ProductoFciDto> findById(Long id) {
        return productoRepository.findById(id).map(productoFciMapper::toDto);
    }
    @Override
    public ProductoFciDto findBySimbolo(String simbolo) {
        if (simbolo == null) {
            throw new ApplicationException("El simbolo no puede ser nulo");
        }
        return productoFciMapper.toDto(productoRepository.findBySimbolo(simbolo));
    }
    @Override
    public List<ProductoFciDto> findByParamPerfilInversor(String perfilInversor) {

 PerfilDeRiesgo.valueOf(perfilInversor);

        return productoRepository.findByPerfilInversor(perfilInversor).stream().map(productoFciMapper::toDto).toList();
    }

    @Override
    public List<ProductoFciDto> findByHorizonteInversion(String horizonteInversion) {
        return productoRepository.findByHorizonteInversion(horizonteInversion).stream().map(productoFciMapper::toDto).toList();
    }

    @Override
    public Iterable<ProductoFciDto> findAll() {
        return productoRepository.findAll().stream().map(productoFciMapper::toDto).toList();
    }

    @Override
    public void deleteById(Long id) {
        if(productoRepository.existsById(id)){
            productoRepository.deleteById(id);
        }
    }

    @Override
    public void updateDataFciNow(){

        fciUpdateService.updateFCI();
    }

    @Override
    public List<ProductoFciDto> getRecomedacionesByPerfilUser(String identificacion){

        WebClient webClient = WebClient.builder()
                .baseUrl("https://xxxxxxx")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return webClient.get()
                .uri("/getRecomendacion/" + identificacion)
                .retrieve()
                .bodyToFlux(ProductoFciDto.class)
                .collectList()
                .block();

    }
}
