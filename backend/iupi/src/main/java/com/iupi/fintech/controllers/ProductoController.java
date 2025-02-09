package com.iupi.fintech.controllers;

import com.iupi.fintech.dtos.ApiResponseDto;
import com.iupi.fintech.dtos.producto.ProductoRequestDto;
import com.iupi.fintech.dtos.producto.ProductoResponseDto;
import com.iupi.fintech.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/productos")
@Tag(name = "Productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    @Operation(summary = "Obtener todos los productos financieros", description = "Devuelve una lista de todos los productos financieros disponibles.")
    public ResponseEntity<ApiResponseDto<ProductoResponseDto>> getAllProductos() {
        Iterable<ProductoResponseDto> productos = productoService.findAll();
        return ResponseEntity.ok(new ApiResponseDto<>(true,"Operación exitotosa",productos));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto financiero por ID", description = "Devuelve un producto financiero basado en el ID proporcionado.")
    public ResponseEntity<ApiResponseDto<ProductoResponseDto>> getProductoById(@PathVariable Long id) {
        Optional<ProductoResponseDto> producto = productoService.findById(id);
        return producto.map(p ->
                ResponseEntity.ok(new ApiResponseDto<>(true, "Producto financiero encontrado", p))
        ).orElseGet(() ->
                ResponseEntity.status(404).body(new ApiResponseDto<>(false, "Producto financiero no encontrado", null))
        );
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo producto financiero", description = "Permite crear un nuevo producto financiero proporcionando los datos requeridos.")
    public ResponseEntity<ApiResponseDto<ProductoResponseDto>>createProducto(@RequestBody ProductoRequestDto producto) {
        ProductoResponseDto nuevoProducto = productoService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto<>(true, "Producto financiero creado",nuevoProducto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto financiero", description = "Actualiza los datos de un producto financiero existente basado en el ID proporcionado.")
    public ResponseEntity<ApiResponseDto<ProductoResponseDto>> updateProducto(@PathVariable Long id, @RequestBody ProductoRequestDto producto) {
        ProductoResponseDto responseDto = productoService.update(id, producto);
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
