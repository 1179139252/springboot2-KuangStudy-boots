package com.hai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.hai.mapper")
public class RedisHotShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisHotShopApplication.class, args);

    }

}
