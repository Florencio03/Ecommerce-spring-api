package com.example.Ecommerce.controllers;

import com.example.Ecommerce.dtos.CheckoutRequest;
import com.example.Ecommerce.dtos.CheckoutResponse;
import com.example.Ecommerce.dtos.ErrorDto;
import com.example.Ecommerce.exceptions.CartEmptyException;
import com.example.Ecommerce.exceptions.CartNotFoundException;
import com.example.Ecommerce.services.CheckoutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping
    public CheckoutResponse checkout(@Valid @RequestBody CheckoutRequest request) {
        return checkoutService.checkout(request);
    }

    @ExceptionHandler({CartNotFoundException.class, CartEmptyException.class})
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(new ErrorDto(ex.getMessage()));
    }

}