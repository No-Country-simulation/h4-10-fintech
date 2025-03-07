package com.iupi.fintech.services.imp;



import com.iupi.fintech.dtos.cuenta.CuentaRequestDto;
import com.iupi.fintech.dtos.tiempo.TiempoDto;
import com.iupi.fintech.dtos.transaccion.TransaccionRequestDto;
import com.iupi.fintech.dtos.transaccion.TransaccionResponseDto;
import com.iupi.fintech.enums.TipoProducto;
import com.iupi.fintech.enums.TransaccionTipoProducto;
import com.iupi.fintech.exceptions.ApplicationException;
import com.iupi.fintech.mappers.timpo.TiempoMapper;
import com.iupi.fintech.mappers.transaccion.TransaccionMapper;
import com.iupi.fintech.models.Cuenta;
import com.iupi.fintech.models.Transaccion;
import com.iupi.fintech.models.User;
import com.iupi.fintech.repositories.TransaccionRepository;
import com.iupi.fintech.repositories.UserRepository;
import com.iupi.fintech.services.TiempoService;
import com.iupi.fintech.services.TransaccionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransaccionServiceImpl implements TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final TransaccionMapper transaccionMapper;
    private final TiempoService tiempoService;
    private final TiempoMapper tiempoMapper;
    private final AuthenticatedUserService authenticatedUser;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public TransaccionResponseDto save(TransaccionRequestDto transaccionRequestDto) {
        String email = authenticatedUser.getAuthenticatedUsername();
        User user = userRepository.findByEmail(email).orElseThrow();

        LocalDate fecha= LocalDate.now();

        TiempoDto tiempo = tiempoService.findByFecha(fecha);
        if(tiempo == null){
            tiempo = tiempoService.save(fecha);
        }

        Transaccion transaccion = transaccionMapper.toEntity(transaccionRequestDto);
        transaccion.setTiempo(tiempoMapper.toEntity(tiempo));
        transaccion.setFecha(LocalDateTime.now());
        //Se setea ya que de momento todas las transacciones seran asi
        transaccion.setTipoProducto(TransaccionTipoProducto.Financiero);

        transaccion.setCuenta(user.getCuenta());

        Transaccion savedTransaccion = transaccionRepository.save(transaccion);
        BigDecimal resultado = user.getCuenta().getMonto().subtract(savedTransaccion.getMonto());

        user.getCuenta().setMonto(resultado);
        userRepository.save(user);

        return transaccionMapper.toResponse(transaccion);
    }

    @Override
    public Optional<TransaccionResponseDto> findById(Long id) {
        return transaccionRepository.findById(id).map(transaccionMapper::toResponse);

    }

    @Override
    public Iterable<TransaccionResponseDto> findAll() {
        return transaccionRepository.findAll().stream()
                .map(transaccionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public TransaccionResponseDto update(Long id, TransaccionRequestDto requestDto) {
        Transaccion transaccion = transaccionRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Tiempo no encontrado con ID: " + id));
        transaccionMapper.updateEntityFromDto(requestDto, transaccion);
        Transaccion transaccionActualizado = transaccionRepository.save(transaccion);
        return transaccionMapper.toResponse(transaccionActualizado);
    }


    public boolean validarTransaccion(Cuenta cuenta, TransaccionRequestDto requestDto){
        if(cuenta.getMonto().compareTo(requestDto.monto()) <= 0){
            throw new ApplicationException("Saldo insuficiente para realizar la transaccion.");
        }
        return true;
    }
}
