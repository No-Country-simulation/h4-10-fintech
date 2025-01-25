package com.iupi.fintech.services.imp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iupi.fintech.models.PreguntasRxFinanciera;
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
            if(inputStream!=null){
                System.out.println(true);
            }
            // Se mapean las preguntas del archivo json
            return objectMapper.readValue(inputStream, new TypeReference<List<PreguntasRxFinanciera>>() {});

    }


}
