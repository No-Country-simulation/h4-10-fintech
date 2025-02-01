package com.iupi.fintech.services.imp;

import com.iupi.fintech.dtos.rxFinanciera.RxFinancieraDto;
import com.iupi.fintech.exceptions.ApplicationException;
import com.iupi.fintech.mappers.rxFinanciera.RxFinancieraMapper;
import com.iupi.fintech.models.rxFinanciera.RxFinanciera;
import com.iupi.fintech.repositories.RxFinancieraRepository;
import com.iupi.fintech.services.RxFinaciera;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RxFinacieraImp implements RxFinaciera {
    private final RxFinancieraMapper rxFinancieraMapper;
private final RxFinancieraRepository rxFinancieraRepository;

    public RxFinacieraImp(RxFinancieraMapper rxFinancieraMapper, RxFinancieraRepository rxFinancieraRepository) {
        this.rxFinancieraMapper = rxFinancieraMapper;
        this.rxFinancieraRepository = rxFinancieraRepository;
    }

    @Override
    public RxFinancieraDto save(RxFinancieraDto requestDTO) {
        if(requestDTO.getUserId() == null){
            throw new ApplicationException("El id del usuario esta vacio");
        }
        RxFinanciera rxFinanciera = rxFinancieraMapper.toEntity(requestDTO);
      rxFinancieraRepository.save(rxFinanciera);
        return rxFinancieraMapper.toResponseDto(rxFinanciera);
    }

    @Override
    public Optional<RxFinancieraDto> findById(Long id) {
        return Optional.empty();
    }

    public Optional<RxFinancieraDto> findByUserId(Long id) {
        return rxFinancieraRepository.findByUser_UsuarioId(id).map(rxFinancieraMapper::toResponseDto);
    }

    @Override
    public Iterable<RxFinancieraDto> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        RxFinanciera rxFinanciera= rxFinancieraRepository.findByUser_UsuarioId(id).orElse(null);
        if(rxFinanciera == null){
            throw new ApplicationException("No se encuentra el registro");
        }
        rxFinancieraRepository.delete(rxFinanciera);
    }

    @Override
    public void updateRxFinanciera(RxFinancieraDto requestDTO, Long id) {
        RxFinanciera rxFinanciera= rxFinancieraRepository.findByUser_UsuarioId(id).orElse(null);
        if(rxFinanciera == null){
            throw new ApplicationException("No se encuentra el registro");
        }
        rxFinancieraMapper.updateEntityFromDto(requestDTO, rxFinanciera);
        rxFinancieraRepository.save(rxFinanciera);
    }
}
