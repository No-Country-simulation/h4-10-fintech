package com.iupi.fintech.services;

import com.iupi.fintech.dtos.user.UserRequestDto;
import com.iupi.fintech.dtos.user.UserResponseDto;
import com.iupi.fintech.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends GenericService<User, Long, UserRequestDto, UserResponseDto>{

    public void changeStatus(Long id);

    public void darDeBaja(Long id);
    public UserResponseDto update(Long id, UserRequestDto userRequestDto);

    public User findByEmail(String email);


}
