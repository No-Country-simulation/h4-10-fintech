package com.iupi.fintech.controllers;

import com.iupi.fintech.dtos.ApiResponseDto;
import com.iupi.fintech.dtos.transaccion.TransaccionRequestDto;
import com.iupi.fintech.dtos.transaccion.TransaccionResponseDto;
import com.iupi.fintech.services.TransaccionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/transacciones")
@Tag(name = "Transacciones", description = "Pendiente de terminar")
@RequiredArgsConstructor
public class TransaccionController {

    private final TransaccionService transaccionService;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una transacción por su ID", description = "Devuelve una transacción basada en el ID proporcionado.")
    public ResponseEntity<ApiResponseDto<TransaccionResponseDto>> getTransaccionById(@PathVariable Long id) {
        Optional<TransaccionResponseDto> transaccion = transaccionService.findById(id);
        return transaccion.map(t -> ResponseEntity.ok(new ApiResponseDto<>(true, "Transacción encontrada", t)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Obtener todas las transacciones", description = "Devuelve una lista de todas las transacciones disponibles.")
    public ResponseEntity<ApiResponseDto<TransaccionResponseDto>> getAllTransacciones() {
        Iterable<TransaccionResponseDto> transacciones = transaccionService.findAll();
        return ResponseEntity.ok(new ApiResponseDto<>(true, "Transacciones encontradas", transacciones));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva transacción", description = "Crea una nueva transacción con los datos proporcionados. (pendiente)")
    public ResponseEntity<ApiResponseDto<TransaccionResponseDto>> createTransaccion(@Valid @RequestBody TransaccionRequestDto transaccionRequestDto) {
        try {
            TransaccionResponseDto transaccionCreada = transaccionService.save(transaccionRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto<>(true, "Transacción creada", transaccionCreada));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponseDto<>(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una transacción", description = "Actualiza una transacción existente con los datos proporcionados.")
    public ResponseEntity<ApiResponseDto<TransaccionResponseDto>> updateTransaccion(@PathVariable Long id, @Valid @RequestBody TransaccionRequestDto transaccionRequestDto) {
        TransaccionResponseDto transaccionActualizada = transaccionService.update(id, transaccionRequestDto);
        return ResponseEntity.ok(new ApiResponseDto<>(true, "Transacción actualizada", transaccionActualizada));
    }

//   @DeleteMapping("/{id}")
//    @Operation(summary = "Eliminar un tiempo", description = "Elimina un tiempo por su ID.")
//    public ResponseEntity<ApiResponseDto<Void>> deleteTransaccion(@PathVariable Long id) {
//        transaccionService.deleteById(id);
//        return ResponseEntity.ok(new ApiResponseDto<>(true, "Transaccion eliminado", null));
//    }

}
