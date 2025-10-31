package com.example.Ecomerce.controllers;

import com.example.Ecomerce.dtos.UserDto;
import com.example.Ecomerce.entities.User;
import com.example.Ecomerce.mappers.UserMapper;
import com.example.Ecomerce.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public Iterable<UserDto> getAllUsers(@RequestParam String sort){
        if (!Set.of("name", "email").contains(sort)){
            sort = "name";
        }

        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
                //.map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
                //.toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        var user = userRepository.findById(id).orElse(null);

        if (user == null){
            return ResponseEntity.notFound().build();
        }
        //var userDto = new UserDto(user.getId(), user.getName(), user.getEmail());
        return ResponseEntity.ok(userMapper.toDto(user));

    }
}
