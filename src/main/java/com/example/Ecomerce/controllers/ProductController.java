package com.example.Ecomerce.controllers;

import com.example.Ecomerce.dtos.ProductDto;
import com.example.Ecomerce.entities.Product;
import com.example.Ecomerce.mappers.ProductMapper;
import com.example.Ecomerce.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping
    public Iterable<ProductDto> getAllProducts(
            @RequestParam(required = false, defaultValue = "", name = "sort") String sort,
            @RequestParam(required = false, name = "categoryId") Byte categoryId
    ){
        List<Product> products;
        if(categoryId != null){
            products = productRepository.findByCategoryId(categoryId);
        }else {
            products = productRepository.findAllWithCategory();
        }
        
        return products
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable long id){
        var product = productRepository.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(productMapper.toDto(product));
    }

}
