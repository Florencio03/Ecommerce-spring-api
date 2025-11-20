package com.example.Ecomerce.mappers;

import com.example.Ecomerce.dtos.CartDto;
import com.example.Ecomerce.dtos.CartItemDto;
import com.example.Ecomerce.entities.Cart;
import com.example.Ecomerce.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    //@Mapping(target = "items", source = "items")
    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);
    //Cart toEntity(CartDto cartDto);
    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
