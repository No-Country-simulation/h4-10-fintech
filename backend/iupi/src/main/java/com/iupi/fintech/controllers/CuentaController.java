package com.iupi.fintech.controllers;

import com.iupi.fintech.dtos.ApiResponseDto;
import com.iupi.fintech.dtos.cuenta.CuentaRequestDto;
import com.iupi.fintech.dtos.cuenta.CuentaResponseDto;
import com.iupi.fintech.services.CuentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/cuentas")
@Tag(name = "Cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService cuentaService;

    @GetMapping
    @Operation(summary = "Obtener todas las cuentas", description = "Devuelve una lista de todas las cuentas disponibles.")
    public ResponseEntity<ApiResponseDto<CuentaResponseDto>> getAllCuentas() {
        Iterable<CuentaResponseDto> cuentas = cuentaService.findAll();
        return ResponseEntity.ok(new ApiResponseDto<>(true, "Operación exitosa", cuentas));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cuenta por ID", description = "Devuelve una cuenta basada en el ID proporcionado.")
    public ResponseEntity<ApiResponseDto<CuentaResponseDto>> getCuentaById(@PathVariable Long id) {
        Optional<CuentaResponseDto> cuenta = cuentaService.findById(id);
        return cuenta.map(c ->
                ResponseEntity.ok(new ApiResponseDto<>(true, "Cuenta encontrada", c))
        ).orElseGet(() ->
                ResponseEntity.status(404).body(new ApiResponseDto<>(false, "Cuenta no encontrada", null))
        );
    }

    @PostMapping
    @Operation(summary = "Crear una nueva cuenta", description = "Permite crear una nueva cuenta proporcionando los datos requeridos.")
    public ResponseEntity<ApiResponseDto<CuentaResponseDto>> createCuenta(@RequestBody CuentaRequestDto cuenta) {
        CuentaResponseDto nuevaCuenta = cuentaService.save(cuenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto<>(true, "Cuenta creada", nuevaCuenta));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una cuenta", description = "Actualiza los datos de una cuenta existente basado en el ID proporcionado.")
    public ResponseEntity<ApiResponseDto<CuentaResponseDto>> updateCuenta(@PathVariable Long id, @RequestBody CuentaRequestDto cuenta) {
        CuentaResponseDto responseDto = cuentaService.update(id, cuenta);
        return ResponseEntity.ok(new ApiResponseDto<>(true, "Cuenta actualizada con éxito", responseDto));
    }

//    @DeleteMapping("/{id}")
//    @Operation(summary = "Eliminar una cuenta", description = "Elimina una cuenta existente basado en el ID proporcionado.")
//    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
//        if (!cuentaService.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
//        cuentaService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
}
