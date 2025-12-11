package com.example.Ecommerce.exceptions;

public class OrderAccessDeniedException extends RuntimeException {
    public OrderAccessDeniedException(Long id) {
        super("You do not have access to order " + id);
    }
}