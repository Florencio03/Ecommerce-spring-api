package com.example.Ecommerce.exceptions;

public class CartEmptyException extends RuntimeException {

    public CartEmptyException(){
        super("Cart is empty");
    }

}
