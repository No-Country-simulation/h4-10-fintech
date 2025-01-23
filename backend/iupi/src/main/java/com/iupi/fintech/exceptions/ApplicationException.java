package com.iupi.fintech.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ApplicationException extends RuntimeException{

    private String campo;

    public ApplicationException(String mensaje) {
        super(mensaje);
    }

    public ApplicationException(String campo, String mensaje) {
        super(mensaje);
        this.campo = campo;
    }

}
