package com.iupi.fintech.controllers;

import com.iupi.fintech.dtos.ApiResponseDto;
import com.iupi.fintech.dtos.objetivoFinanciero.ObjetivoFinancieroRequestDto;
import com.iupi.fintech.dtos.objetivoFinanciero.ObjetivoFinancieroResponseDto;
import com.iupi.fintech.services.ObjetivoFinancieroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/objetivos-financieros")
@Tag(name = "Objetivos Financieros")
@RequiredArgsConstructor
public class ObjetivoFinancieroController {

    private final ObjetivoFinancieroService objetivoFinancieroService;

    @GetMapping
    @Operation(summary = "Obtener todos los objetivos financieros", description = "Devuelve una lista de todos los objetivos financieros establecidos.")
    public ResponseEntity<ApiResponseDto<ObjetivoFinancieroResponseDto>> getAllObjetivosFinancieros() {
        Iterable<ObjetivoFinancieroResponseDto> objetivos = objetivoFinancieroService.findAll();
        return ResponseEntity.ok(new ApiResponseDto<>(true, "Operación exitosa", objetivos));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener objetivo financiero por ID", description = "Devuelve un objetivo financiero basado en el ID proporcionado.")
    public ResponseEntity<ApiResponseDto<ObjetivoFinancieroResponseDto>> getObjetivoFinancieroById(@PathVariable Long id) {
        Optional<ObjetivoFinancieroResponseDto> objetivo = objetivoFinancieroService.findById(id);
        return objetivo.map(o ->
                ResponseEntity.ok(new ApiResponseDto<>(true, "Objetivo financiero encontrado", o))
        ).orElseGet(() ->
                ResponseEntity.status(404).body(new ApiResponseDto<>(false, "Objetivo financiero no encontrado", null))
        );
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo objetivo financiero", description = "Permite crear un nuevo objetivo financiero proporcionando los datos requeridos.")
    public ResponseEntity<ApiResponseDto<ObjetivoFinancieroResponseDto>> createObjetivoFinanciero(@Valid @RequestBody ObjetivoFinancieroRequestDto objetivo) {
        ObjetivoFinancieroResponseDto nuevoObjetivo = objetivoFinancieroService.save(objetivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto<>(true, "Objetivo financiero creado", nuevoObjetivo));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un objetivo financiero", description = "Actualiza los datos de un objetivo financiero existente basado en el ID proporcionado.")
    public ResponseEntity<ApiResponseDto<ObjetivoFinancieroResponseDto>> updateObjetivoFinanciero(@PathVariable Long id, @RequestBody ObjetivoFinancieroRequestDto objetivo) {
        ObjetivoFinancieroResponseDto responseDto = objetivoFinancieroService.update(id, objetivo);
        return ResponseEntity.ok(new ApiResponseDto<>(true, "Objetivo financiero actualizado con éxito", responseDto));
    }
}
