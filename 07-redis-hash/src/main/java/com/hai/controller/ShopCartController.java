package com.hai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopCartController {


    @GetMapping("/index")
    public String shopcart(){
        return "shopcart";
    }



}
