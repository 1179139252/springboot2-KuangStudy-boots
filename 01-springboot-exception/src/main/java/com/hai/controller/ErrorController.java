package com.hai.controller;

import com.hai.pojo.User;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

    @GetMapping("/error1")
    public User error1(@RequestParam("id") Integer id) {
        if (id ==1){

            throw  new RuntimeException("运行出异常");
        }

        User user = new User();
        user.setAge(22);
        user.setName("阿飞");
        user.setCity("重庆");
        user.setSex("男");

        return user;
    }


    @GetMapping("/error2")
    public User error2(@RequestParam("id") String id) {

        int i = 1 / 0;

        User user = new User();
        user.setAge(22);
        user.setName("阿飞");
        user.setCity("重庆");
        user.setSex("男");
        return user;
    }

    @GetMapping("/getusername")
    public User getusername(@RequestParam("id") String id) {

        int i = 1 / 0;

        User user = new User();
        user.setAge(22);
        user.setName("阿飞");
        user.setCity("重庆");
        user.setSex("男");
        return user;
    }

    @GetMapping("/getuser")
    public String getuser(@RequestParam("id") String id) {

        return "succful";
    }


}
