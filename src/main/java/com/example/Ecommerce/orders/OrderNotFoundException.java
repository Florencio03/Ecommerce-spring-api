package com.example.Ecommerce.orders;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Order " + id + " not found");
    }
}
