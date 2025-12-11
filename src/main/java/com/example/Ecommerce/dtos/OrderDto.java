package com.example.Ecommerce.dtos;

import com.example.Ecommerce.entities.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {
    private Long id;
    private String status;
    private LocalDateTime createdAt;

    private List<OrderItemDto> items;

    private BigDecimal totalPrice;

}
