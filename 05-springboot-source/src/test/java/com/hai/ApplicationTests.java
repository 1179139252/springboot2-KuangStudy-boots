package com.hai;

import com.hai.inportSelect.OrderServiceBean;
import com.hai.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class ApplicationTests {


    @Autowired
    UserService userService;


    @Autowired
    ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        System.out.println(applicationContext);
        userService.saveUser();
    }

    @Autowired
    OrderServiceBean orderServiceBean;

    @Test
    void importSelectTest(){
        System.out.println(applicationContext);
        orderServiceBean.show();

    }

}
