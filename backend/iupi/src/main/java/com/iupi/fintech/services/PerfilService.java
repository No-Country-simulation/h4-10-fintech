package com.iupi.fintech.services;

import com.iupi.fintech.dtos.perfil.PerfilDto;
import com.iupi.fintech.models.Perfil;
import com.iupi.fintech.models.User;
import org.springframework.stereotype.Service;

@Service
public interface PerfilService extends GenericServiceDto<Perfil,PerfilDto> {

PerfilDto findPerfilByUserId(Long userId);

}
