package com.example.Ecommerce.carts;

public class CartEmptyException extends RuntimeException {

    public CartEmptyException(){
        super("Cart is empty");
    }

}
