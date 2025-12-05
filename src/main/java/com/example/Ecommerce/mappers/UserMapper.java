package com.example.Ecommerce.mappers;

import com.example.Ecommerce.dtos.RegisterUserRequest;
import com.example.Ecommerce.dtos.UpdateUserRequest;
import com.example.Ecommerce.dtos.UserDto;
import com.example.Ecommerce.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);
}
