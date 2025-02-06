package com.iupi.fintech.mappers.perfil;

import com.iupi.fintech.dtos.perfil.PerfilDto;
import com.iupi.fintech.models.Perfil;
import com.iupi.fintech.models.User;
import com.iupi.fintech.repositories.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PerfilMapper {
 @Autowired
 private UserRepository userRepository;

 @Mapping(source = "userId", target = "user", qualifiedByName = "longToUser")
 public abstract Perfil toEntity(PerfilDto dto);

 @Mapping(source = "user", target = "userId", qualifiedByName = "userToLong")
 public abstract PerfilDto toResponseDto(Perfil entity);
 @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
 public abstract void updateEntityFromDto(PerfilDto perfilDTO, @MappingTarget Perfil perfil);

 //public abstract void updateEntityFromDto(ProductoFinancieroRequestDto dto, @MappingTarget ProductoFinanciero productoFinanciero);

 // Convierte el long del Dto en un usuario
 @Named("longToUser")
 public User toUser(Long id) {

  return id!=null? userRepository.findById(id).orElse(null):null;
 }

 // Convierte el user de la entida en un long para el dto
 @Named("userToLong")
 public Long toLong(User user) {
     return user!=null?user.getUsuarioId():null;
 }


}
