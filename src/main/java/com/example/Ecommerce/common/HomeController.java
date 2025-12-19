package com.example.Ecommerce.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    //Change endpoint
    @RequestMapping("/placeholderHome")
    public String index(Model model){
        model.addAttribute("name", "Alba");
        return "index";
    }
}
