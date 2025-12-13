package com.example.Ecommerce.services;

import com.example.Ecommerce.entities.Order;

public interface PaymentGateway {
    CheckoutSession createCheckoutSession(Order order);

}
