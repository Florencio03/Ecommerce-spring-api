package com.example.Ecommerce.mappers;

import com.example.Ecommerce.dtos.OrderDto;
import com.example.Ecommerce.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
}
