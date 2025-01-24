package com.iupi.fintech.services.imp;

import com.iupi.fintech.dtos.user.UserRequestDto;
import com.iupi.fintech.dtos.user.UserResponseDto;
import com.iupi.fintech.enums.EstadoRegistro;
import com.iupi.fintech.exceptions.ApplicationException;
import com.iupi.fintech.mappers.user.UserMapper;
import com.iupi.fintech.models.User;
import com.iupi.fintech.repositories.UserRepository;
import com.iupi.fintech.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        User user = userRepository.save(userMapper.toEntity(userRequestDto));

        return userMapper.toResponseDTO(user);
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto userRequestDto) {

        if (!userRepository.existsById(id)) {
            throw new ApplicationException("usuario no encontrado con id: " + id);
        }

        User user = userMapper.toEntity(userRequestDto);
        user.setUsuarioId(id);
        return userMapper.toResponseDTO(userRepository.save(user));
    }

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
            }
            if ("INACTIVO".equals(user.get().getEstadoRegistro())) {
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

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: " + email));
    }
}
