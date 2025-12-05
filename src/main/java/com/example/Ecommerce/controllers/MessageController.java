package com.example.Ecommerce.controllers;

import com.example.Ecommerce.entities.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    //Change endpoint
    @RequestMapping("/placeholderMessage")
    public Message sayHello() {
        return new Message("Hello World!");
    }
}
