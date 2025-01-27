package com.iupi.fintech.services;

import com.iupi.fintech.dtos.Preguntas_respuestas_perfil.RespuestaUsuario;
import com.iupi.fintech.dtos.perfil.PerfilDto;
import com.iupi.fintech.models.Perfil;
import com.iupi.fintech.models.User;
import com.iupi.fintech.services.imp.PreguntasService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PerfilService extends GenericServiceDto<Perfil,PerfilDto> {

PerfilDto findPerfilByUserId(Long userId);

void updatePerfil(List<RespuestaUsuario> respuestas, Long id);

}
