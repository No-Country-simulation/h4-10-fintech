package com.iupi.fintech.services.imp;

import com.iupi.fintech.dtos.user.UserRequestDto;
import com.iupi.fintech.dtos.user.UserResponseDto;
import com.iupi.fintech.enums.*;
import com.iupi.fintech.exceptions.ApplicationException;
import com.iupi.fintech.mappers.user.UserMapper;
import com.iupi.fintech.models.Perfil;
import com.iupi.fintech.models.User;
import com.iupi.fintech.repositories.UserRepository;
import com.iupi.fintech.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImp(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {

       User newUser= userMapper.toEntity(userRequestDto);

        return userMapper.toResponseDTO(userRepository.save(newUser));
    }
    @Override
    public User saveFirstUser(UserRequestDto userRequestDto) {
        User newUser= userMapper.toEntity(userRequestDto);
        newUser.setEstadoRegistro(EstadoRegistro.INACTIVO);
        newUser.setFechaRegistro(LocalDate.now());
        Perfil perfil= new Perfil();
        perfil.setPerfilRiesgo(PerfilDeRiesgo.CONSERVADOR);
        perfil.setCapacidadAhorro(CapacidadDeAhorro.BAJO);
        perfil.setConocimientoFinanciero(ConocimientoFinanciero.NOVATO);
        perfil.setNivelEconomico(NivelEconomico.BAJO);
        newUser.setPerfil(perfil);
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto userRequestDto) {

            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new ApplicationException("Usuario no encontrado con ID: " + id));

            // Usar el mapper para copiar los valores no nulos
            userMapper.updateUserFromDto(userRequestDto, existingUser);
            existingUser.setEstadoRegistro(EstadoRegistro.ACTIVO);

      userRepository.save(existingUser);
        return userMapper.toResponseDTO(existingUser);
        }
     //


    @Override
    public Optional<UserResponseDto> findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.map(userMapper::toResponseDTO);
        } else {
            throw new ApplicationException("usuario no encontrado con id: " + id);
        }
    }

    @Override
    public Iterable<UserResponseDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toResponseDTO).toList();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }




    //-----------------------Dar de baja, eliminar o cambiar estados -----------------------------
    @Override
    public void deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }
    @Override
    public void changeStatus(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            if ("ACTIVO".equals(user.get().getEstadoRegistro())) {
                user.get().setEstadoRegistro(EstadoRegistro.INACTIVO);
            } else {
                user.get().setEstadoRegistro(EstadoRegistro.ACTIVO);
            }
            userRepository.save(user.get());
        } else {
            throw new ApplicationException("usuario no encontrado con id: " + id);
        }
    }
    @Override
    public void darDeBaja(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setEstadoRegistro(EstadoRegistro.ELIMINADO);
            userRepository.save(user.get());
        } else {
            throw new ApplicationException("usuario no encontrado con id: " + id);
        }
    }

}
