package com.iupi.fintech.services.imp;

import com.iupi.fintech.dtos.perfil.PerfilDto;
import com.iupi.fintech.enums.CapacidadDeAhorro;
import com.iupi.fintech.enums.ConocimientoFinanciero;
import com.iupi.fintech.enums.NivelEconomico;
import com.iupi.fintech.enums.PerfilDeRiesgo;
import com.iupi.fintech.exceptions.ApplicationException;
import com.iupi.fintech.mappers.perfil.PerfilMapper;
import com.iupi.fintech.models.Perfil;
import com.iupi.fintech.repositories.PerfilRepository;
import com.iupi.fintech.services.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerfilServiceImp implements PerfilService {
    private final PerfilRepository perfilRepository;
    private final PerfilMapper perfilMapper;

    @Autowired
    public PerfilServiceImp(PerfilRepository perfilRepository, PerfilMapper perfilMapper) {
        this.perfilRepository = perfilRepository;
        this.perfilMapper = perfilMapper;
    }

    @Override
    public PerfilDto save(PerfilDto requestDTO) {

       Perfil perfil= perfilMapper.toEntity(requestDTO);
       perfil.setPerfilRiesgo(PerfilDeRiesgo.CONSERVADOR);
       perfil.setCapacidadAhorro(CapacidadDeAhorro.BAJO);
       perfil.setConocimientoFinanciero(ConocimientoFinanciero.NOVATO);
       perfil.setNivelEconomico(NivelEconomico.BAJO);

       return perfilMapper.toResponseDto(perfilRepository.save(perfil));


    }

    @Override
    public Optional<PerfilDto> findById(Long id) {

        Optional<Perfil> perfil = perfilRepository.findById(id);
        return perfil.map(perfilMapper::toResponseDto);

    }

    @Override
    public Iterable<PerfilDto> findAll() {
   return perfilRepository.findAll().stream().map(perfilMapper::toResponseDto).toList();
    }

    @Override
    public void deleteById(Long id) {
        if (perfilRepository.existsById(id)) {
            perfilRepository.deleteById(id);
        } else {
            throw new ApplicationException("Perfil no encontrado con id: " + id);
        }
    }

    @Override
    public PerfilDto findPerfilByUserId(Long id) {
     Perfil perfil = perfilRepository.findByUser_UsuarioId(id);
     return perfilMapper.toResponseDto(perfil);

    }

    @Override
    public PerfilDto updatePerfil(PerfilDto requestDTO) {

return null;
    }
}
