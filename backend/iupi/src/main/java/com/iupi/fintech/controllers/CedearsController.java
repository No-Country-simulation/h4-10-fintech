package com.iupi.fintech.controllers;


import com.iupi.fintech.dtos.ApiResponseDto;
import com.iupi.fintech.dtos.ProductoFciDto;
import com.iupi.fintech.enums.PerfilDeRiesgo;
import com.iupi.fintech.exceptions.ApplicationException;
import com.iupi.fintech.services.ProductoFciService;
import com.iupi.fintech.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/fci")
public class CedearsController {

    //    private final IolApiService iolApiService;
//    private final IolDataInterface iolDataInterface;
    private final UserService userService;
    private final ProductoFciService productoFciService;

    @Autowired
    public CedearsController(UserService userService, ProductoFciService productoFciService) {
        this.userService = userService;
        this.productoFciService = productoFciService;
    }

    @GetMapping("/update")
    @Operation(summary = "Actualizar Fondos Comunes de Inversión", description = "Actualiza la información de Fondos Comunes de Inversión.")
    public ResponseEntity<ApiResponseDto<String>> update() {
        try {


            productoFciService.updateDataFciNow();
            return ResponseEntity.ok(new ApiResponseDto<>(true, "Fondos Comunes de Inversión actualizados", null));
        } catch (ApplicationException e) {
            return new ResponseEntity<>(new ApiResponseDto<>(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/titulos")
    @Operation(summary = "Obtener todos los títulos de Fondos Comunes de Inversión", description = "Devuelve una lista de todos los títulos de Fondos Comunes de Inversión disponibles.")
    public ResponseEntity<ApiResponseDto<ProductoFciDto>> getTitulosFCI() {

//        OidcUser oidcUser = (OidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String userIupi = (String) oidcUser.getClaims().get("email");
        Iterable<ProductoFciDto> productoFCI = productoFciService.findAll();

        return ResponseEntity.ok(new ApiResponseDto<>(true, "Operación exitosa", productoFCI));
    }

    @GetMapping("{simbolo}")
    @Operation(summary = "Obtener un título de Fondos Comunes de Inversión por su símbolo (Identificador)", description = "Devuelve un título de Fondos Comunes de Inversión basado en el símbolo proporcionado.")
    public ResponseEntity<ApiResponseDto<ProductoFciDto>> getTituloBySimbolo(@PathVariable String simbolo) {

        try {
//            OidcUser oidcUser = (OidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            String userIupi = (String) oidcUser.getClaims().get("email");
//            ProductoFCI iolData = iolDataInterface.getIolDataBySimbolo(userIupi, simbolo);

            ProductoFciDto fci = productoFciService.findBySimbolo(simbolo);

            return ResponseEntity.ok(new ApiResponseDto<>(true, "Operación exitosa", fci));
        } catch (ApplicationException e) {
            return new ResponseEntity(new ApiResponseDto<>(false, "No se ha encontrado el título: " + e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/riesgo/{riesgo}")
    @Operation(summary = "Obtener todos los títulos de Fondos Comunes de Inversión por el riesgo indicado", description = "Devuelve una lista de todos los títulos de Fondos Comunes de Inversión disponibles por riesgo.")
    public ResponseEntity<ApiResponseDto<ProductoFciDto>> getTitulosFCIByRiesgo(@PathVariable PerfilDeRiesgo riesgo) {
        try {
            List<ProductoFciDto> fci = productoFciService.findByParamPerfilInversor(riesgo.name());

            return ResponseEntity.ok(new ApiResponseDto<>(true, "Operación exitosa", fci));
        } catch (ApplicationException e) {
            return new ResponseEntity(new ApiResponseDto<>(false, "No se ha encontrado el título: " + e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/plazo/{horizonteInversion}")
    @Operation(summary = "Obtener todos los títulos de Fondos Comunes de Inversión por el plazo indicado", description = "Devuelve una lista de todos los títulos de Fondos Comunes de Inversión disponibles por plazo.")
    public ResponseEntity<ApiResponseDto<ProductoFciDto>> getTitulosFCIByPlazo(
            @Parameter(name = "horizonteInversion", description = "Plazo del FCI", examples = {
                    @ExampleObject(name = "30 dias", value = "Corto Plazo"),
                    @ExampleObject(name = "180 dias", value = "Mediano Plazo"),
                    @ExampleObject(name = "300 dias", value = "Largo Plazo")
            })  @PathVariable String horizonteInversion){
        try {
            List<ProductoFciDto> fci = productoFciService.findByHorizonteInversion(horizonteInversion);

            return ResponseEntity.ok(new ApiResponseDto<>(true, "Operación exitosa", fci));
        } catch (ApplicationException e) {
            return new ResponseEntity(new ApiResponseDto<>(false, "No se ha encontrado el título: " + e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

}

