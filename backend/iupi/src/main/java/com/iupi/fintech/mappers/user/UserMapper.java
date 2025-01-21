package com.iupi.fintech.mappers.user;

import com.iupi.fintech.dtos.user.UserInfo;
import com.iupi.fintech.dtos.user.UserRequestDto;
import com.iupi.fintech.dtos.user.UserResponseDto;
import com.iupi.fintech.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

   public abstract User toEntity(UserRequestDto dto);

    public abstract UserResponseDto toResponseDTO(User user);

    @Mapping(target = "auth0Id", source = "sub")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "name", source = "name")
   public abstract User toEntitySave(UserInfo userinfo);

}
