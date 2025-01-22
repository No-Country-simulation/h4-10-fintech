package com.iupi.fintech.controllers;

import com.iupi.fintech.dtos.ApiResponseDto;
import com.iupi.fintech.dtos.productoFinanciero.ProductoFinancieroRequestDto;
import com.iupi.fintech.dtos.productoFinanciero.ProductoFinancieroResponseDto;
import com.iupi.fintech.services.ProductoFinancieroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/productos-financieros")
@Tag(name = "Productos Financieros", description = "Operaciones relacionadas con los productos financieros")
@RequiredArgsConstructor
public class ProductoFinancieroController {

    private ProductoFinancieroService productoFinancieroService;

    @GetMapping
    @Operation(summary = "Obtener todos los productos financieros", description = "Devuelve una lista de todos los productos financieros disponibles.")
    public ResponseEntity<ApiResponseDto<ProductoFinancieroResponseDto>> getAllProductos() {
        Iterable<ProductoFinancieroResponseDto> productos = productoFinancieroService.findAll();
        return ResponseEntity.ok(new ApiResponseDto<>(true,"Operación exitotosa",productos));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto financiero por ID", description = "Devuelve un producto financiero basado en el ID proporcionado.")
    public ResponseEntity<ApiResponseDto<ProductoFinancieroResponseDto>> getProductoById(@PathVariable Long id) {
        Optional<ProductoFinancieroResponseDto> producto = productoFinancieroService.findById(id);
        return producto.map(p ->
                ResponseEntity.ok(new ApiResponseDto<>(true, "Producto financiero encontrado", p))
        ).orElseGet(() ->
                ResponseEntity.status(404).body(new ApiResponseDto<>(false, "Producto financiero no encontrado", null))
        );
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo producto financiero", description = "Permite crear un nuevo producto financiero proporcionando los datos requeridos.")
    public ResponseEntity<ApiResponseDto<ProductoFinancieroResponseDto>>createProducto(@RequestBody ProductoFinancieroRequestDto producto) {
        ProductoFinancieroResponseDto nuevoProducto = productoFinancieroService.save(producto);
        return ResponseEntity.ok(new ApiResponseDto<>(true, "Producto financiero creado",nuevoProducto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto financiero", description = "Actualiza los datos de un producto financiero existente basado en el ID proporcionado.")
    public ResponseEntity<ApiResponseDto<ProductoFinancieroResponseDto>> updateProducto(@PathVariable Long id, @RequestBody ProductoFinancieroRequestDto producto) {
        ProductoFinancieroResponseDto responseDto = productoFinancieroService.update(id, producto);
        return ResponseEntity.ok(new ApiResponseDto<>(true, "Producto financiero actualizado con éxito", responseDto));
    }

//    @DeleteMapping("/{id}")
//    @Operation(summary = "Eliminar un producto financiero", description = "Elimina un producto financiero existente basado en el ID proporcionado.")
//    public ResponseEntity<Void> deleteProducto(@PathVariable Integer id) {
//        if (!productoFinancieroRepository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
//        productoFinancieroRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
}
