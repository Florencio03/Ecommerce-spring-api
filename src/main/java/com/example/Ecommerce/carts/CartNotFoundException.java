package com.example.Ecommerce.carts;

public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException() {
        super("Cart not found");
    }

}
