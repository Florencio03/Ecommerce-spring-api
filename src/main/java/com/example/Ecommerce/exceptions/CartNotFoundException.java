package com.example.Ecommerce.exceptions;

public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException() {
        super("Cart not found");
    }

}
