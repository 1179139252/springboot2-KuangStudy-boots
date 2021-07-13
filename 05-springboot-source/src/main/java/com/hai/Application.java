package com.hai;

import com.hai.inportSelect.OrderEnbleConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OrderEnbleConfiguration//自定义引入import select
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
