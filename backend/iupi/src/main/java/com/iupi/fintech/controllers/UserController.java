package com.iupi.fintech.controllers;

import com.iupi.fintech.dtos.ApiResponseDto;
import com.iupi.fintech.dtos.user.UserRequestDto;
import com.iupi.fintech.dtos.user.UserResponseDto;
import com.iupi.fintech.exceptions.ApplicationException;
import com.iupi.fintech.models.User;
import com.iupi.fintech.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene al usuario actual")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> getUser(@PathVariable("id") Long id) {

        try{
            Optional<UserResponseDto> response = userService.findById(id);
            if (response.isPresent()) {
                UserResponseDto user = response.get();
                return ResponseEntity.ok(new ApiResponseDto<>(true, "Usuario encontrado", user));
            } else {
                return new ResponseEntity<>(new ApiResponseDto<>(false, "Usuario no encontrado", null), HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
           return new ResponseEntity<>(new ApiResponseDto<>(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/e/{email}")
    @Operation(summary = "Obtiene al usuario actual")
    public ResponseEntity<ApiResponseDto<User>> getUserr(@PathVariable("email") String email) {

        try{
            User response = userService.findByEmail(email);
            if (response != null) {

                return ResponseEntity.ok(new ApiResponseDto<>(true, "Usuario encontrado", response));
            } else {
                return new ResponseEntity<>(new ApiResponseDto<>(false, "Usuario no encontrado", null), HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponseDto<>(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }


    @PatchMapping("/{id}")
    @Operation(summary = "Actualiza al usuario actual")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> updateUser(@PathVariable("id") Long id, @RequestBody UserRequestDto user) {

        try{
            Optional<UserResponseDto> response = userService.findById(id);
            if (response.isPresent()) {
                userService.update(id, user);
                UserResponseDto userResponseDto = response.get();
                return ResponseEntity.ok(new ApiResponseDto<>(true, "Usuario actualizado", userResponseDto));
            } else {
                return new ResponseEntity<>(new ApiResponseDto<>(false, "Usuario no encontrado", null), HttpStatus.NOT_FOUND);
            }
        }catch (ApplicationException e){
            return new ResponseEntity<>(new ApiResponseDto<>(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Dar de baja al usuario actual")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> deleteUser(@PathVariable("id") Long id) {

        try {
            Optional<UserResponseDto> response = userService.findById(id);
            if (response.isPresent()) {
                userService.darDeBaja(id);
                UserResponseDto userResponseDto = response.get();
                return ResponseEntity.ok(new ApiResponseDto<>(true, "Usuario dado de baja", userResponseDto));
            } else {
                return new ResponseEntity<>(new ApiResponseDto<>(false, "Usuario no encontrado", null), HttpStatus.NOT_FOUND);
            }
        } catch (ApplicationException e) {
            return new ResponseEntity<>(new ApiResponseDto<>(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }




}
