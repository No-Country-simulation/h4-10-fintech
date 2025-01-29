package com.iupi.fintech.controllers;

import com.iupi.fintech.dtos.ApiResponseDto;
import com.iupi.fintech.dtos.Preguntas_respuestas_perfil.RespuestaUsuario;
import com.iupi.fintech.dtos.perfil.PerfilDto;
import com.iupi.fintech.dtos.Preguntas_respuestas_perfil.PreguntasRxFinanciera;
import com.iupi.fintech.exceptions.ApplicationException;
import com.iupi.fintech.services.PerfilService;
import com.iupi.fintech.services.imp.PreguntasService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/perfil")
public class PerfilController {

    private final PerfilService perfilService;
    private final PreguntasService preguntasService;

    @Autowired
    public PerfilController(PerfilService perfilService, PreguntasService preguntasService) {
        this.perfilService = perfilService;
        this.preguntasService = preguntasService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene el perfil de un usuario", description = "Devuelve el perfil de un usuario basado en el ID proporcionado del usuario.")
    public ResponseEntity<ApiResponseDto<PerfilDto>> getPerfilByUserId(@PathVariable Long id) {

        try {
            PerfilDto response = perfilService.findPerfilByUserId(id);
            return ResponseEntity.ok(new ApiResponseDto<>(true, "Perfil encontrado", response));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponseDto<>(false, "Perfil no encontrado", null), HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/preguntas")
    @Operation(summary = "Obtiene las preguntas de Rx financiera", description = "Devuelve las preguntas de Rx financiera")
    public ResponseEntity<List<PreguntasRxFinanciera>> getPreguntas() throws IOException {
try {
    List<PreguntasRxFinanciera> preguntas = preguntasService.cargarPreguntasDesdeJson();
    return ResponseEntity.ok(preguntas);
} catch (IOException e) {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
}
    }


@PostMapping("/preguntas/{id}")
@Operation(summary = "Actualiza el perfil de un usuario",
        description = "Actualiza el perfil de un usuario, luego de responder las preguntas para el perfil financiero")
public ResponseEntity<ApiResponseDto<String>> updatePerfil(@PathVariable Long id, @RequestBody List<RespuestaUsuario> respuestas) {

        try{
        if (respuestas.isEmpty()) {
            return new ResponseEntity<>(new ApiResponseDto<>(false, "Respuestas no proporcionadas", null), HttpStatus.BAD_REQUEST);
        }
        perfilService.updatePerfil(respuestas,id);
        return ResponseEntity.ok(new ApiResponseDto<>(true, "Perfil actualizado",null));
} catch (ApplicationException e) {
            return new ResponseEntity<>(new ApiResponseDto<>(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
}


}

