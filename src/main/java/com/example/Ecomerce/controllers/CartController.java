package com.example.Ecomerce.controllers;

import com.example.Ecomerce.dtos.AddItemToCartRequest;
import com.example.Ecomerce.dtos.CartDto;
import com.example.Ecomerce.dtos.CartItemDto;
import com.example.Ecomerce.entities.Cart;
import com.example.Ecomerce.entities.CartItem;
import com.example.Ecomerce.mappers.CartMapper;
import com.example.Ecomerce.repositories.CartRepository;
import com.example.Ecomerce.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable(name = "cartId") UUID cartId){

        var cart = cartRepository.getCartWithItems(cartId).orElse(null);

        if(cart == null){
            return ResponseEntity.notFound().build(); //404
        }

        return ResponseEntity.ok(cartMapper.toDto(cart));
    }

    @PostMapping
    public ResponseEntity<CartDto> createCart(
            UriComponentsBuilder uriBuilder){
        var cart = new Cart();
        cartRepository.save(cart);

        var cartDto = cartMapper.toDto(cart);
        var uri = uriBuilder.path("/carts/{id}").buildAndExpand(cartDto.getId()).toUri();
        return ResponseEntity.created(uri).body(cartDto);
    }

    @PostMapping("{cartId}/items")
    public ResponseEntity<CartItemDto> addToCart(
            @PathVariable(name = "cartId") UUID cartId,
            @RequestBody AddItemToCartRequest request){

        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null){
            return ResponseEntity.notFound().build(); //404
        }

        var product = productRepository.findById(request.getProductId()).orElse(null);
        if (product == null){
            return ResponseEntity.badRequest().build(); //400
        }

        //Check if product already exists in the cart
        var cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if (cartItem != null){
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }else {
            //add new item
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cart.getItems().add(cartItem);
        }

        cartRepository.save(cart);

        var cartItemDto = cartMapper.toDto(cartItem);

        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }
}
