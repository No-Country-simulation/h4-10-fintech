package com.iupi.fintech.services.imp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iupi.fintech.dtos.perfil.PerfilDto;
import com.iupi.fintech.dtos.Preguntas_respuestas_perfil.PreguntasRxFinanciera;
import com.iupi.fintech.dtos.Preguntas_respuestas_perfil.RespuestaUsuario;
import com.iupi.fintech.enums.CapacidadDeAhorro;
import com.iupi.fintech.enums.ConocimientoFinanciero;
import com.iupi.fintech.enums.NivelEconomico;
import com.iupi.fintech.enums.PerfilDeRiesgo;
import com.iupi.fintech.exceptions.ApplicationException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class PreguntasService {

    public List<PreguntasRxFinanciera> cargarPreguntasDesdeJson() throws IOException {

        // Se carga el archivo json desde su direccion
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("PreguntasRxFinanciera.json");

        if (inputStream == null) {
            throw new FileNotFoundException("No se pudo encontrar el archivo preguntas_respuestas.json en los recursos.");
        }
        // Se mapean las preguntas del archivo json
        return objectMapper.readValue(inputStream, new TypeReference<List<PreguntasRxFinanciera>>() {
        });
    }

    public PerfilDto actualizarPerfil(List<RespuestaUsuario> respuestas) {
        PerfilDto perfil = new PerfilDto();
        for (RespuestaUsuario respuesta : respuestas) {

            switch (respuesta.getIdPregunta()) {
                // Define el nivel economico del cliente
                case 1:
                    perfil.setNivelEconomico(updateNivelEconomico(respuesta));
                    break;

                // Define la capacidad de ahorro que pueda tener el cliente
                case 2:
                    perfil.setCapacidadAhorro(updateCapacidadDeAhorro(respuesta));
                    break;

                // Define el conocimiento financiero del cliente
                case 3:
                    perfil.setConocimientoFinanciero(updateConocimientoFinanciero(respuesta));
                    break;

                // Define el perfil de riesgo del cliente
                case 4:
                    perfil.setPerfilRiesgo(updatePerfilDeRiesgo(respuesta));
                    break;
                case 5:
                    perfil.setObjetivoPrincipal(defineObjetivoPrincipal(respuesta));
                    break;
            }
        }
        return perfil;
    }

    public NivelEconomico updateNivelEconomico(RespuestaUsuario respuesta) {
        String nivelEconomico;
        if (respuesta.getIdRespuesta() == 1) {
            nivelEconomico = "BAJO";
        } else if (respuesta.getIdRespuesta() == 2 || respuesta.getIdRespuesta() == 3) {
            nivelEconomico = "MEDIO";
        } else {
            nivelEconomico = "ALTO";
        }
        return NivelEconomico.valueOf(nivelEconomico);
    }

    public CapacidadDeAhorro updateCapacidadDeAhorro(RespuestaUsuario respuesta) {
        String capacidadDeAhorro;
        if (respuesta.getIdRespuesta() == 1) {
            capacidadDeAhorro = "BAJO";
        } else if (respuesta.getIdRespuesta() == 2 || respuesta.getIdRespuesta() == 3) {
            capacidadDeAhorro = "MEDIO";
        } else {
            capacidadDeAhorro = "ALTO";
        }
        return CapacidadDeAhorro.valueOf(capacidadDeAhorro);
    }

    public ConocimientoFinanciero updateConocimientoFinanciero(RespuestaUsuario respuesta) {
        String conocimientoFinanciero;
        if (respuesta.getIdRespuesta() == 1 || respuesta.getIdRespuesta() == 2) {
            conocimientoFinanciero = "NOVATO";
        } else if (respuesta.getIdRespuesta() == 3) {
            conocimientoFinanciero = "INTERMEDIO";
        } else {
            conocimientoFinanciero = "AVANZADO";
        }
        return ConocimientoFinanciero.valueOf(conocimientoFinanciero);
    }

    public PerfilDeRiesgo updatePerfilDeRiesgo(RespuestaUsuario respuesta) {
        String perfilDeRiesgo;
        if (respuesta.getIdRespuesta() == 1) {
            perfilDeRiesgo = "CONSERVADOR";
        } else if (respuesta.getIdRespuesta() == 2 || respuesta.getIdRespuesta() == 3) {
            perfilDeRiesgo = "MODERADO";
        } else {
            perfilDeRiesgo = "ARRIESGADO";
        }
        return PerfilDeRiesgo.valueOf(perfilDeRiesgo);
    }

    public String defineObjetivoPrincipal(RespuestaUsuario respuesta) {
        String objetivoPrincipal = "";
        if (respuesta.getIdRespuesta() == 1) {
            objetivoPrincipal = "Ahorrar para una meta específica (vacaciones, un auto, etc.).";
        } else if (respuesta.getIdRespuesta() == 2) {
            objetivoPrincipal = "Generar ingresos pasivos con inversiones";
        } else if (respuesta.getIdRespuesta() == 3) {
            objetivoPrincipal = "Asegurar mi retiro o patrimonio a largo plazo.";
        } else if (respuesta.getIdRespuesta() == 4) {
            objetivoPrincipal = respuesta.getRespuestaTexto();
        }
        return objetivoPrincipal;

    }

    public void validarRespuestas(RespuestaUsuario respuesta) {
        if (respuesta.getIdRespuesta() < 1 || respuesta.getIdRespuesta() > 4) {
            throw new ApplicationException("Respuesta inválida, debe seleccionar una opcion valida");
        }
    }

}
