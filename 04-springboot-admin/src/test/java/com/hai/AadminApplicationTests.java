package com.hai;

import com.hai.service.user.UserServiceImpl;
import com.hai.pojo.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AadminApplicationTests {


    @Autowired
    UserServiceImpl userService;

    @Test
    void contextLoads() {

        User user = new User();
        user.setAge(22);
        user.setMale(1);
        user.setNickname("阿飞呀");
        user.setPassword("2242421442");
        boolean save = userService.save(user);

    }

    @Test
    void getByid(){




    }

}
