package com.iupi.fintech.mappers.rxFinanciera;

import com.iupi.fintech.dtos.rxFinanciera.RxFinancieraDto;
import com.iupi.fintech.models.User;
import com.iupi.fintech.models.rxFinanciera.RxFinanciera;
import com.iupi.fintech.repositories.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class RxFinancieraMapper {

    @Autowired
    private UserRepository userRepository;

    @Mapping(source = "userId", target = "user", qualifiedByName = "longToUser")
  public abstract RxFinanciera toEntity(RxFinancieraDto dto);

    @Mapping(source = "user", target = "userId", qualifiedByName = "userToLong")
  public abstract RxFinancieraDto toResponseDto(RxFinanciera entity);

    public abstract void updateEntityFromDto(RxFinancieraDto dto, @MappingTarget RxFinanciera entity);

    @Named("longToUser")
    public User toUser(Long id) {

        return id!=null? userRepository.findById(id).orElse(null):null;
    }
    @Named("userToLong")
    public Long toLong(User user) {
        return user!=null?user.getUsuarioId():null;
    }

}
