package com.example.Ecomerce.mappers;

import com.example.Ecomerce.dtos.RegisterUserRequest;
import com.example.Ecomerce.dtos.UpdateUserRequest;
import com.example.Ecomerce.dtos.UserDto;
import com.example.Ecomerce.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);
}
