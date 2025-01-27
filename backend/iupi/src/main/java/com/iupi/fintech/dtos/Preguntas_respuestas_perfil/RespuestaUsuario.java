package com.iupi.fintech.dtos.Preguntas_respuestas_perfil;

import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class RespuestaUsuario {

    private int idPregunta;
    private int idRespuesta;
    private String respuestaTexto;

}
