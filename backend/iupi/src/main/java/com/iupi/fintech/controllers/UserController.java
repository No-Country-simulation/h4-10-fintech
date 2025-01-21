package com.iupi.fintech.controllers;

import com.iupi.fintech.dtos.ApiResponseDto;
import com.iupi.fintech.dtos.user.UserResponseDto;
import com.iupi.fintech.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/perfil")
    @Operation(summary = "Obtiene al usuario actual")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> getUser(@RequestParam("id") Long id) {

        try{
            Optional<UserResponseDto> response = userService.findById(id);
            if (response.isPresent()) {
                UserResponseDto user = response.get();
                return ResponseEntity.ok(new ApiResponseDto<>(true, "Usuario encontrado", user));
            } else {
                return ResponseEntity.ok(new ApiResponseDto<>(false, "Usuario no encontrado", null));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }




}
