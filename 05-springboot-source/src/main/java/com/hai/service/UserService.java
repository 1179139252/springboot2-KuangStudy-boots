package com.hai.service;

import com.hai.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class UserService {


    @Autowired
    private User user;

    public void saveUser() {

        log.info("你保存的用户是{}",user);
    }

}
