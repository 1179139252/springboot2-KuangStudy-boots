package com.hai.controller;

import com.hai.common.R;
import com.hai.common.RResponseEnum;
import com.hai.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setAge(22);
        user.setName("阿飞");
        user.setCity("重庆");
        user.setSex("男");

        return user;
    }


}
