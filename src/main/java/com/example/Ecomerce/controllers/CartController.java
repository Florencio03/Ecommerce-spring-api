package com.example.Ecomerce.controllers;

import com.example.Ecomerce.dtos.AddItemToCartRequest;
import com.example.Ecomerce.dtos.CartDto;
import com.example.Ecomerce.dtos.CartItemDto;
import com.example.Ecomerce.dtos.UpdateCartItemRequest;
import com.example.Ecomerce.exceptions.CartNotFoundException;
import com.example.Ecomerce.exceptions.ProductNotFoundException;
import com.example.Ecomerce.mappers.CartMapper;
import com.example.Ecomerce.repositories.CartRepository;
import com.example.Ecomerce.repositories.ProductRepository;
import com.example.Ecomerce.services.CartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartDto> createCart(
            UriComponentsBuilder uriBuilder
    ) {
        var cartDto = cartService.createCart();
        var uri = uriBuilder.path("/carts/{id}").buildAndExpand(cartDto.getId()).toUri();

        return ResponseEntity.created(uri).body(cartDto);
    }

    @GetMapping("/{cartId}")
    public CartDto getCart(@PathVariable(name = "cartId") UUID cartId){
        return cartService.getCart(cartId);
    }

    @PostMapping("{cartId}/items")
    public ResponseEntity<CartItemDto> addToCart(
            @PathVariable(name = "cartId") UUID cartId,
            @RequestBody AddItemToCartRequest request){

        var cartItemDto = cartService.addToCart(cartId, request.getProductId());

        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @PutMapping("/{cartId}/items/{productId}")
    //Because your controller method no longer decides HTTP codes you don't need ResponseEntity.
    public CartItemDto updateItem(
            @PathVariable("cartId") UUID cartId,
            @PathVariable("productId") Long productId,
            @Valid @RequestBody UpdateCartItemRequest request
    ){

        return cartService.updateItem(cartId, productId, request.getQuantity());
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> removeItem(
            @PathVariable("cartId") UUID cartId,
            @PathVariable("productId") Long productId
    ){

        cartService.removeItem(cartId,productId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<Valid> clearCart(@PathVariable UUID cartId){

        cartService.clearCart(cartId);

        return ResponseEntity.noContent().build();
    }

//    @ExceptionHandler(CartNotFoundException.class)
//    public ResponseEntity<Map<String, String>> handleCartNotFound(){
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(
//                        Map.of("error", "Cart not found.")
//                );
//    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<Map<String, String>> handleProduct(){
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(Map.of("error", "Product not found in the cart."));
//    }

}
