package com.iupi.fintech.services.imp;

import com.iupi.fintech.models.PreguntasRxFinanciera;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PreguntasServiceTest {

    PreguntasService preguntasService = new PreguntasService();
    @Test
    void cargarPreguntasDesdeJson() throws IOException {

      List<PreguntasRxFinanciera> as=  preguntasService.cargarPreguntasDesdeJson();
       for (PreguntasRxFinanciera preguntasRxFinanciera : as) {
           System.out.println(preguntasRxFinanciera.toString());
       };
    }

}