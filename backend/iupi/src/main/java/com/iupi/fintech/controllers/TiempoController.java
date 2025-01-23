package com.iupi.fintech.controllers;

import com.iupi.fintech.dtos.ApiResponseDto;
import com.iupi.fintech.dtos.tiempo.TiempoRequestDto;
import com.iupi.fintech.dtos.tiempo.TiempoResponseDto;
import com.iupi.fintech.services.TiempoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/tiempos")
@Tag(name = "Tiempos")
@RequiredArgsConstructor
public class TiempoController {

    private final TiempoService tiempoService;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un tiempo por su ID", description = "Devuelve un tiempo basado en el ID proporcionado.")
    public ResponseEntity<ApiResponseDto<TiempoResponseDto>> getTiempoById(@PathVariable Long id) {
        Optional<TiempoResponseDto> tiempo = tiempoService.findById(id);
        return tiempo.map(t -> ResponseEntity.ok(new ApiResponseDto<>(true, "Tiempo encontrado", t)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Obtener todos los tiempos", description = "Devuelve una lista de todos los tiempos disponibles.")
    public ResponseEntity<ApiResponseDto<TiempoResponseDto>> getAllTiempos() {
        Iterable<TiempoResponseDto> tiempos = tiempoService.findAll();
        return ResponseEntity.ok(new ApiResponseDto<>(true, "Tiempos encontrados", tiempos));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo tiempo", description = "Crea un nuevo tiempo con los datos proporcionados.")
    public ResponseEntity<ApiResponseDto<TiempoResponseDto>> createTiempo(@Valid @RequestBody TiempoRequestDto tiempoRequestDto) {
        TiempoResponseDto tiempoCreado = tiempoService.save(tiempoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto<>(true, "Tiempo creado", tiempoCreado));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un tiempo", description = "Actualiza un tiempo existente con los datos proporcionados.")
    public ResponseEntity<ApiResponseDto<TiempoResponseDto>> updateTiempo(@PathVariable Long id, @Valid @RequestBody TiempoRequestDto tiempoRequestDto) {
        TiempoResponseDto tiempoActualizado = tiempoService.update(id, tiempoRequestDto);
        return ResponseEntity.ok(new ApiResponseDto<>(true, "Tiempo actualizado", tiempoActualizado));
    }

//    @DeleteMapping("/{id}")
//    @Operation(summary = "Eliminar un tiempo", description = "Elimina un tiempo por su ID.")
//    public ResponseEntity<ApiResponseDto<Void>> deleteTiempo(@PathVariable Long id) {
//        tiempoService.deleteById(id);
//        return ResponseEntity.ok(new ApiResponseDto<>(true, "Tiempo eliminado", null));
//    }



}
