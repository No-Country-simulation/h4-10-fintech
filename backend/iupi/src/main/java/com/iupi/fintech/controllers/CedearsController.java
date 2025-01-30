package com.iupi.fintech.controllers;


import com.iupi.fintech.dtos.ApiResponseDto;
import com.iupi.fintech.models.generic.FondosComunInversion;
import com.iupi.fintech.utils.iol.IolApiService;
import com.iupi.fintech.utils.iol.IolDataInterface;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/invertir")
public class CedearsController {

    private final IolApiService iolApiService;
    private final IolDataInterface iolDataInterface;

    @Autowired
    public CedearsController(IolApiService iolApiService, IolDataInterface iolDataInterface) {
        this.iolApiService = iolApiService;
        this.iolDataInterface = iolDataInterface;
    }

    @GetMapping("/titulos/fci")
    @Operation(summary = "Obtener todos los títulos de Fondos Comunes de Inversión", description = "Devuelve una lista de todos los títulos de Fondos Comunes de Inversión disponibles.")
    public ResponseEntity<ApiResponseDto<FondosComunInversion>> getTitulosFCI() {

String userIupi = "angeljsdev@gmail.com";

        System.out.println(userIupi);
        List<FondosComunInversion> iolData = iolDataInterface.getIolData(userIupi);


        return ResponseEntity.ok(new ApiResponseDto<>(true, "Operación exitosa",iolData));


    }



}
