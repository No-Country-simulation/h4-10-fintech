package com.iupi.fintech.mappers.user;

import com.iupi.fintech.dtos.user.UserRequestDto;
import com.iupi.fintech.dtos.user.UserResponseDto;
import com.iupi.fintech.models.Perfil;
import com.iupi.fintech.models.UserInfo;
import com.iupi.fintech.models.User;
import com.iupi.fintech.repositories.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class UserMapper {
    @Autowired
    private UserRepository userRepository;

   // @Mapping(target = "authorities", ignore = true)
   public abstract User toEntity(UserRequestDto dto);

    @Mapping(source = "perfil", target = "perfilId", qualifiedByName = "perfilToLong")
   // @Mapping(source = "user", target = "user", qualifiedByName = "userToLong")
    @Mapping(source="user", target = "edad", qualifiedByName = "calculateAge" )
    public abstract UserResponseDto toResponseDTO(User user);

    @Mapping(source = "sub", target = "auth0Id")
    @Mapping( source = "email", target = "email")
    @Mapping( source = "name" , target = "nombre")
   public abstract User toEntitySave(UserInfo userInfo);

    @Named("userToLong")
    public Long toLong(User user) {
        return user!=null ? user.getUsuarioId() : null;
    }

    @Named("longToUser")
    public User toUser(Long id) {
        return id!=null? userRepository.findById(id).orElse(null):null;
    }
    @Named("calculateAge")
    public Integer calculateAge(User user) {
        int anio= new Date().getYear();
        if(user.getFechaNacimiento() == null) return 0;
        return anio- user.getFechaNacimiento().getYear();
    }

    @Named("perfilToLong")
    public Long toLong(Perfil perfil) {
        return perfil!= null ? perfil.getPerfilId() : null;

    }

    @Named("longToPerfil")
    private Perfil toPerfil(Long id) {
        if (id != null) {
            return userRepository.findById(id).orElse(null).getPerfil();
        }else {
            return null;
        }
    }

}