package com.iupi.fintech.controllers;

import com.iupi.fintech.dtos.ApiResponseDto;
import com.iupi.fintech.dtos.perfil.PerfilDto;
import com.iupi.fintech.services.PerfilService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/perfil")
public class PerfilController {

    private final PerfilService perfilService;

    @Autowired
    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene el perfil de un usuario")
    public ResponseEntity<ApiResponseDto<PerfilDto>> getPerfilByUserId(@PathVariable Long id) {

        try {
            PerfilDto response = perfilService.findPerfilByUserId(id);
            return ResponseEntity.ok(new ApiResponseDto<>(true, "Perfil encontrado", response));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponseDto<>(false, "Perfil no encontrado", null), HttpStatus.NOT_FOUND);
        }

    }





}

