package com.iupi.fintech.mappers.user;

import com.iupi.fintech.dtos.user.UserRequestDto;
import com.iupi.fintech.dtos.user.UserResponseDto;
import com.iupi.fintech.models.UserInfo;
import com.iupi.fintech.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "authorities", ignore = true)
   public abstract User toEntity(UserRequestDto dto);

    public abstract UserResponseDto toResponseDTO(User user);

    @Mapping(source = "sub", target = "auth0Id")
    @Mapping( source = "email", target = "email")
    @Mapping( source = "name" , target = "nombre")
   public abstract User toEntitySave(UserInfo userInfo);

}