package com.iupi.fintech.dtos.Preguntas_respuestas_perfil;

import lombok.*;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class PreguntasRxFinanciera {

        private int id;
        private String pregunta;
List<RespuestaRxFinanciera> opciones;

}
