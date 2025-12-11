package com.example.Ecommerce.services;

import com.example.Ecommerce.dtos.OrderDto;
import com.example.Ecommerce.exceptions.OrderAccessDeniedException;
import com.example.Ecommerce.exceptions.OrderNotFoundException;
import com.example.Ecommerce.mappers.OrderMapper;
import com.example.Ecommerce.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {
    private final AuthService authService;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    /**
     * Get all orders from the currently authenticated user
     */
    public List<OrderDto> getAllOrders() {
        var user = authService.getCurrentUser();
        var orders = orderRepository.getOrdersByCustomer(user);

        return orders.stream().map(orderMapper::toDto).toList();
    }

    /**
     * Get a single order by ID for the current authenticated user
     * (So a user cannot access other users’ orders)
     */
    public OrderDto getOrderById(Long orderId) {
        var user = authService.getCurrentUser();

        var order = orderRepository.getOrderWithItems(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        if (!order.isPlacedBy(user)) {
            throw new OrderAccessDeniedException(orderId);
        }

        return orderMapper.toDto(order);
    }

}
