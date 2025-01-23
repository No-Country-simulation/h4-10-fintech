package com.iupi.fintech.services.imp;

import com.iupi.fintech.dtos.user.UserRequestDto;
import com.iupi.fintech.dtos.user.UserResponseDto;
import com.iupi.fintech.exceptions.ApplicationException;
import com.iupi.fintech.mappers.user.UserMapper;
import com.iupi.fintech.models.User;
import com.iupi.fintech.repositories.UserRepository;
import com.iupi.fintech.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserServiceImp implements UserService  {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImp(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public Optional<UserResponseDto> findById(Long id) {
       Optional<User> user = userRepository.findById(id);
       if (user.isPresent()){
         return  user.map(userMapper::toResponseDTO);
       } else {
           throw new ApplicationException("id not found");
       }


    }

    @Override
    public Iterable<UserResponseDto> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
