package com.example.Ecomerce.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddItemToCartRequest {
    @NotNull(message = "ID cannot be null")
    private Long productId;
}
