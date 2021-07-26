package com.hai.controller;


import com.hai.service.LuckRandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LuckRandController {

    @Autowired
    LuckRandService luckRandService;


//    开始抽奖
    @PostMapping("/user/luck")
    public int start(){

        int luck = luckRandService.luckyStart();

        return luck;
    }

}
