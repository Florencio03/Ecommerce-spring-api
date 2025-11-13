package com.example.Ecomerce.mappers;

import com.example.Ecomerce.dtos.ProductDto;
import com.example.Ecomerce.dtos.UserDto;
import com.example.Ecomerce.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);
}
