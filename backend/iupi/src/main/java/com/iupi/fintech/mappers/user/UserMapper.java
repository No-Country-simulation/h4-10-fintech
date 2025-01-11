package com.iupi.fintech.mappers.user;

import com.iupi.fintech.dtos.user.UserRequestDto;
import com.iupi.fintech.dtos.user.UserResponseDto;
import com.iupi.fintech.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

   public abstract User toEntity(UserRequestDto dto);

    public abstract UserResponseDto toResponseDTO(User user);

}
