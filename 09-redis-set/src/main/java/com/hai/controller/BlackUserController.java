package com.hai.controller;


import com.hai.service.BlackUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlackUserController {

    @Autowired
    BlackUserService blackUserService;

//   添加黑名单
    @PostMapping("/user/black/add")
    public String addBlackUser(Long userId){

        Long aLong = blackUserService.addBlackUsers(userId);

        if (aLong ==1){

            return "添加成功！";

        }else {

            return "添加失败";
        }
    }

    @PostMapping("/user/black/rem")
    public String removeBlackUser(Long userId){

        Long aLong = blackUserService.removeBlackUsers(userId);

        if (aLong ==1){
            return "移出成功！";

        }else {

            return "移出失败";
        }
    }


}
