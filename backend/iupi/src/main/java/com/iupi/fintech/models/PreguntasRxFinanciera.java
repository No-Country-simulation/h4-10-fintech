package com.iupi.fintech.models;

import lombok.*;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class PreguntasRxFinanciera {

        private int id;
        private String pregunta;
        private List<String> opciones;


}
