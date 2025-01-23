package com.iupi.fintech.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDto<T>  implements Serializable {

    boolean estado;
    String message;
    T data;
    Iterable<T> dataIterable;
    List<Iterable<T>> dataListIterable;


    public ApiResponseDto(boolean estado, String message, T data) {
        this.estado = estado;
        this.message = message;
        this.data = data;
    }

    public ApiResponseDto(boolean estado, String message, Iterable<T> dataIterable) {
        this.estado = estado;
        this.message = message;
        this.dataIterable = dataIterable;
    }

    public ApiResponseDto(boolean estado, String message, List<Iterable<T>> dataListIterable) {
        this.estado = estado;
        this.message = message;
        this.dataListIterable = dataListIterable;
    }
}
